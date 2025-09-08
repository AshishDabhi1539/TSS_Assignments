package com.tss.banking.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {
    private Long id;
    private Long accountId;
    private String type;
    private double amount;
    private LocalDateTime date;
    private String description;
    private String status;
}