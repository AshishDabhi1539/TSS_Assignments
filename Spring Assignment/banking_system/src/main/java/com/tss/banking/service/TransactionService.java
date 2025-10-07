package com.tss.banking.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tss.banking.dto.request.TransactionRequestDto;
import com.tss.banking.dto.response.TransactionResponseDto;

public interface TransactionService {
    TransactionResponseDto createTransaction(TransactionRequestDto dto);
    TransactionResponseDto createTransactionForCustomer(TransactionRequestDto dto, String customerEmail);
    TransactionResponseDto getTransactionById(Long id);
    List<TransactionResponseDto> getTransactionsByAccountId(Long accountId);
    Page<TransactionResponseDto> getTransactionsByAccountId(Long accountId, Pageable pageable);
    Page<TransactionResponseDto> getTransactionsByAccountIdForCustomer(Long accountId, String customerEmail, Pageable pageable);
    Page<TransactionResponseDto> getTransactionsByCustomerId(Long customerId, Pageable pageable);
    Page<TransactionResponseDto> getTransactionsByCustomerEmail(String customerEmail, Pageable pageable);
}