package com.tss.banking.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tss.banking.dto.request.TransactionRequestDto;
import com.tss.banking.dto.response.TransactionResponseDto;
import com.tss.banking.entity.Account;
import com.tss.banking.entity.Transaction;
import com.tss.banking.entity.User;
import com.tss.banking.entity.enums.TransactionStatus;
import com.tss.banking.entity.enums.TransactionType;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.AccountRepository;
import com.tss.banking.repository.TransactionRepository;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.ReferenceNumberService;
import com.tss.banking.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReferenceNumberService referenceNumberService;

    @Override
    @Transactional
    public TransactionResponseDto createTransaction(TransactionRequestDto dto) {
        // Generate idempotency key if not provided
        if (dto.getIdempotencyKey() == null || dto.getIdempotencyKey().isEmpty()) {
            dto.setIdempotencyKey(generateIdempotencyKey(dto));
        }
        
        // Check for idempotency
        Transaction existingTransaction = transactionRepo.findByIdempotencyKey(dto.getIdempotencyKey());
        if (existingTransaction != null) {
            return mapper.map(existingTransaction, TransactionResponseDto.class);
        }

        TransactionType type;
        try {
            type = TransactionType.valueOf(dto.getType().toUpperCase());
        } catch (Exception ex) {
            throw new BankApiException("Invalid transaction type: " + dto.getType());
        }

        if (type == TransactionType.TRANSFER) {
            return processTransferTransaction(dto);
        } else {
            return processSingleAccountTransaction(dto, type);
        }
    }

    private TransactionResponseDto processSingleAccountTransaction(TransactionRequestDto dto, TransactionType type) {
        Account account = accountRepo.findByAccountNumber(dto.getAccountNumber())
                .orElseThrow(() -> new BankApiException("Account not found with number: " + dto.getAccountNumber()));

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType(type);
        transaction.setAmount(dto.getAmount());
        transaction.setDescription(dto.getDescription());
        transaction.setDate(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setIdempotencyKey(dto.getIdempotencyKey());
        transaction.setReferenceNumber(referenceNumberService.generateTransactionReference());
        transaction.setBalanceBefore(account.getBalance());
        
        // Set account numbers for clarity
        if (type == TransactionType.DEBIT) {
            transaction.setFromAccountNumber(account.getAccountNumber());
        } else if (type == TransactionType.CREDIT) {
            transaction.setToAccountNumber(account.getAccountNumber());
        }

        // Validate sufficient balance for debit
        if (type == TransactionType.DEBIT && account.getBalance().compareTo(dto.getAmount()) < 0) {
            transaction.setStatus(TransactionStatus.FAILED);
            transaction.setFailureReason("Insufficient balance");
            transactionRepo.save(transaction);
            throw new BankApiException("Insufficient balance for debit transaction.");
        }

        // Update account balance
        if (type == TransactionType.DEBIT) {
            account.setBalance(account.getBalance().subtract(dto.getAmount()));
        } else if (type == TransactionType.CREDIT) {
            account.setBalance(account.getBalance().add(dto.getAmount()));
        }

        transaction.setBalanceAfter(account.getBalance());
        transaction.setStatus(TransactionStatus.COMPLETED);
        
        Transaction savedTransaction = transactionRepo.save(transaction);
        accountRepo.save(account);
        
        return mapper.map(savedTransaction, TransactionResponseDto.class);
    }

    private TransactionResponseDto processTransferTransaction(TransactionRequestDto dto) {
        if (dto.getToAccountNumber() == null || dto.getToAccountNumber().isEmpty()) {
            throw new BankApiException("To Account Number is required for transfer transactions");
        }

        Account fromAccount = accountRepo.findByAccountNumber(dto.getAccountNumber())
                .orElseThrow(() -> new BankApiException("From account not found with number: " + dto.getAccountNumber()));
        
        Account toAccount = accountRepo.findByAccountNumber(dto.getToAccountNumber())
                .orElseThrow(() -> new BankApiException("To account not found with number: " + dto.getToAccountNumber()));

        // Validate sufficient balance
        if (fromAccount.getBalance().compareTo(dto.getAmount()) < 0) {
            throw new BankApiException("Insufficient balance for transfer transaction.");
        }

        // Create debit transaction for from account
        Transaction debitTransaction = new Transaction();
        debitTransaction.setAccount(fromAccount);
        debitTransaction.setRecipientAccount(toAccount);
        debitTransaction.setType(TransactionType.TRANSFER);
        debitTransaction.setAmount(dto.getAmount());
        debitTransaction.setDescription("Transfer to account " + dto.getToAccountNumber() + ": " + dto.getDescription());
        debitTransaction.setDate(LocalDateTime.now());
        debitTransaction.setIdempotencyKey(dto.getIdempotencyKey());
        debitTransaction.setReferenceNumber(referenceNumberService.generateTransferReference());
        debitTransaction.setBalanceBefore(fromAccount.getBalance());
        debitTransaction.setFromAccountNumber(fromAccount.getAccountNumber());
        debitTransaction.setToAccountNumber(toAccount.getAccountNumber());
        
        // Update from account balance
        fromAccount.setBalance(fromAccount.getBalance().subtract(dto.getAmount()));
        debitTransaction.setBalanceAfter(fromAccount.getBalance());
        debitTransaction.setStatus(TransactionStatus.COMPLETED);

        // Create credit transaction for to account
        Transaction creditTransaction = new Transaction();
        creditTransaction.setAccount(toAccount);
        creditTransaction.setRecipientAccount(fromAccount);
        creditTransaction.setType(TransactionType.TRANSFER);
        creditTransaction.setAmount(dto.getAmount());
        creditTransaction.setDescription("Transfer from account " + dto.getAccountNumber() + ": " + dto.getDescription());
        creditTransaction.setDate(LocalDateTime.now());
        creditTransaction.setReferenceNumber(referenceNumberService.generateTransferReference());
        creditTransaction.setBalanceBefore(toAccount.getBalance());
        creditTransaction.setFromAccountNumber(fromAccount.getAccountNumber());
        creditTransaction.setToAccountNumber(toAccount.getAccountNumber());
        
        // Update to account balance
        toAccount.setBalance(toAccount.getBalance().add(dto.getAmount()));
        creditTransaction.setBalanceAfter(toAccount.getBalance());
        creditTransaction.setStatus(TransactionStatus.COMPLETED);

        // Save all changes
        Transaction savedDebitTransaction = transactionRepo.save(debitTransaction);
        transactionRepo.save(creditTransaction);
        accountRepo.save(fromAccount);
        accountRepo.save(toAccount);

        return mapper.map(savedDebitTransaction, TransactionResponseDto.class);
    }

    @Override
    public TransactionResponseDto getTransactionById(Long id) {
        Transaction transaction = transactionRepo.findById(id)
                .orElseThrow(() -> new BankApiException("Transaction not found with ID: " + id));
        return mapper.map(transaction, TransactionResponseDto.class);
    }

    @Override
    public List<TransactionResponseDto> getTransactionsByAccountId(Long accountId) {
        List<Transaction> transactions = transactionRepo.findByAccountIdOrderByDateDesc(accountId);
        return transactions.stream()
                .map(transaction -> mapper.map(transaction, TransactionResponseDto.class))
                .toList();
    }

    @Override
    public Page<TransactionResponseDto> getTransactionsByAccountId(Long accountId, Pageable pageable) {
        Page<Transaction> transactions = transactionRepo.findByAccountIdOrderByDateDesc(accountId, pageable);
        return transactions.map(transaction -> mapper.map(transaction, TransactionResponseDto.class));
    }

    @Override
    public Page<TransactionResponseDto> getTransactionsByCustomerId(Long customerId, Pageable pageable) {
        Page<Transaction> transactions = transactionRepo.findByCustomerIdOrderByDateDesc(customerId, pageable);
        return transactions.map(transaction -> mapper.map(transaction, TransactionResponseDto.class));
    }

    @Override
    @Transactional
    public TransactionResponseDto createTransactionForCustomer(TransactionRequestDto dto, String customerEmail) {
        // Validate that the account belongs to the customer
        Account account = accountRepo.findByAccountNumber(dto.getAccountNumber())
                .orElseThrow(() -> new BankApiException("Account not found with number: " + dto.getAccountNumber()));
        
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new BankApiException("Customer not found"));
        
        if (!account.getCustomer().getId().equals(customer.getId())) {
            throw new BankApiException("Access denied: You can only perform transactions on your own accounts");
        }
        
        // For transfer transactions, validate the from account ownership
        if ("TRANSFER".equalsIgnoreCase(dto.getType()) && dto.getToAccountNumber() != null) {
            Account toAccount = accountRepo.findByAccountNumber(dto.getToAccountNumber())
                    .orElseThrow(() -> new BankApiException("To account not found with number: " + dto.getToAccountNumber()));
            
            // Allow transfers to any account, but validate from account ownership
            return createTransaction(dto);
        }
        
        return createTransaction(dto);
    }

    @Override
    public Page<TransactionResponseDto> getTransactionsByAccountIdForCustomer(Long accountId, String customerEmail, Pageable pageable) {
        // Validate that the account belongs to the customer
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new BankApiException("Account not found with ID: " + accountId));
        
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new BankApiException("Customer not found"));
        
        if (!account.getCustomer().getId().equals(customer.getId())) {
            throw new BankApiException("Access denied: You can only view transactions for your own accounts");
        }
        
        return getTransactionsByAccountId(accountId, pageable);
    }

    @Override
    public Page<TransactionResponseDto> getTransactionsByCustomerEmail(String customerEmail, Pageable pageable) {
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new BankApiException("Customer not found"));
        
        return getTransactionsByCustomerId(customer.getId(), pageable);
    }
    
    private String generateIdempotencyKey(TransactionRequestDto dto) {
        return referenceNumberService.generateIdempotencyKey("TXN", 
            dto.getAccountNumber(), dto.getType(), dto.getAmount());
    }
}