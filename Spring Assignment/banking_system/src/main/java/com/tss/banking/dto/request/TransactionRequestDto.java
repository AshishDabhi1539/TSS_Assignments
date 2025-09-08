package com.tss.banking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TransactionRequestDto {
    private Long accountId;
    private String type; // e.g., DEBIT, CREDIT, TRANSFER
    private double amount;
    private String description;
}