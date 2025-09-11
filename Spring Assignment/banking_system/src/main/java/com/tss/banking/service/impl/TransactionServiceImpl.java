package com.tss.banking.service.impl;

import java.math.BigDecimal;
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
import com.tss.banking.entity.enums.TransactionStatus;
import com.tss.banking.entity.enums.TransactionType;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.AccountRepository;
import com.tss.banking.repository.TransactionRepository;
import com.tss.banking.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private ModelMapper mapper;

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
        transaction.setAmount(BigDecimal.valueOf(dto.getAmount()));
        transaction.setDescription(dto.getDescription());
        transaction.setDate(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setIdempotencyKey(dto.getIdempotencyKey());
        transaction.setBalanceBefore(account.getBalance());

        // Validate sufficient balance for debit
        if (type == TransactionType.DEBIT && account.getBalance().compareTo(BigDecimal.valueOf(dto.getAmount())) < 0) {
            transaction.setStatus(TransactionStatus.FAILED);
            transaction.setFailureReason("Insufficient balance");
            transactionRepo.save(transaction);
            throw new BankApiException("Insufficient balance for debit transaction.");
        }

        // Update account balance
        if (type == TransactionType.DEBIT) {
            account.setBalance(account.getBalance().subtract(BigDecimal.valueOf(dto.getAmount())));
        } else if (type == TransactionType.CREDIT) {
            account.setBalance(account.getBalance().add(BigDecimal.valueOf(dto.getAmount())));
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
        if (fromAccount.getBalance().compareTo(BigDecimal.valueOf(dto.getAmount())) < 0) {
            throw new BankApiException("Insufficient balance for transfer transaction.");
        }

        // Create debit transaction for from account
        Transaction debitTransaction = new Transaction();
        debitTransaction.setAccount(fromAccount);
        debitTransaction.setType(TransactionType.DEBIT);
        debitTransaction.setAmount(BigDecimal.valueOf(dto.getAmount()));
        debitTransaction.setDescription("Transfer to account " + dto.getToAccountNumber() + ": " + dto.getDescription());
        debitTransaction.setDate(LocalDateTime.now());
        debitTransaction.setIdempotencyKey(dto.getIdempotencyKey());
        debitTransaction.setBalanceBefore(fromAccount.getBalance());
        
        // Update from account balance
        fromAccount.setBalance(fromAccount.getBalance().subtract(BigDecimal.valueOf(dto.getAmount())));
        debitTransaction.setBalanceAfter(fromAccount.getBalance());
        debitTransaction.setStatus(TransactionStatus.COMPLETED);

        // Create credit transaction for to account
        Transaction creditTransaction = new Transaction();
        creditTransaction.setAccount(toAccount);
        creditTransaction.setType(TransactionType.CREDIT);
        creditTransaction.setAmount(BigDecimal.valueOf(dto.getAmount()));
        creditTransaction.setDescription("Transfer from account " + dto.getAccountNumber() + ": " + dto.getDescription());
        creditTransaction.setDate(LocalDateTime.now());
        creditTransaction.setBalanceBefore(toAccount.getBalance());
        
        // Update to account balance
        toAccount.setBalance(toAccount.getBalance().add(BigDecimal.valueOf(dto.getAmount())));
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
    
    private String generateIdempotencyKey(TransactionRequestDto dto) {
        // Generate unique idempotency key based on transaction details
        String base = dto.getAccountNumber() + "_" + dto.getType() + "_" + dto.getAmount() + "_" + System.currentTimeMillis();
        return "TXN_" + base.hashCode();
    }
}