package com.tss.banking.service.impl;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        Account account = accountRepo.findById(dto.getAccountId())
                .orElseThrow(() -> new BankApiException("Account not found with ID: " + dto.getAccountId()));

        Transaction transaction = mapper.map(dto, Transaction.class);
        transaction.setAccount(account);
        transaction.setDate(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.PENDING);

        TransactionType type;
        try {
            type = TransactionType.valueOf(dto.getType().toUpperCase());
        } catch (Exception ex) {
            throw new BankApiException("Invalid transaction type: " + dto.getType());
        }
        transaction.setType(type);

        transaction.setBalanceBefore(account.getBalance());

        if (type == TransactionType.DEBIT && account.getBalance() < dto.getAmount()) {
            throw new BankApiException("Insufficient balance for debit transaction.");
        }

        if (type == TransactionType.DEBIT) {
            account.setBalance(account.getBalance() - dto.getAmount());
        } else if (type == TransactionType.CREDIT) {
            account.setBalance(account.getBalance() + dto.getAmount());
        }

        transaction.setBalanceAfter(account.getBalance());
        transaction.setStatus(TransactionStatus.COMPLETED);
        Transaction savedTransaction = transactionRepo.save(transaction);
        accountRepo.save(account); // Update balance
        return mapper.map(savedTransaction, TransactionResponseDto.class);
    }

    @Override
    public TransactionResponseDto getTransactionById(Long id) {
        Transaction transaction = transactionRepo.findById(id)
                .orElseThrow(() -> new BankApiException("Transaction not found with ID: " + id));
        return mapper.map(transaction, TransactionResponseDto.class);
    }
}