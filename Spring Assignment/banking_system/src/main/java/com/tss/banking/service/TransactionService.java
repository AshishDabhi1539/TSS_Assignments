package com.tss.banking.service;

import com.tss.banking.dto.request.TransactionRequestDto;
import com.tss.banking.dto.response.TransactionResponseDto;

public interface TransactionService {
    TransactionResponseDto createTransaction(TransactionRequestDto dto);
    TransactionResponseDto getTransactionById(Long id);
}