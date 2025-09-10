package com.tss.banking.dto.response;

import java.math.BigDecimal;
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
    private Long recipientAccountId;
    private String type;
    private BigDecimal amount;
    private BigDecimal feeAmount;
    private LocalDateTime date;
    private String description;
    private String status;
    private String referenceNumber;
}