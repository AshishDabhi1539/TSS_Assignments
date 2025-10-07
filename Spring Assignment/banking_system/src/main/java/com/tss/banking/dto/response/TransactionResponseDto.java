package com.tss.banking.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {
    private Long id;
    private String accountNumber;
    private String recipientAccountNumber;
    private String type;
    private BigDecimal amount;
    private BigDecimal feeAmount;
    private BigDecimal balanceBefore;
    private BigDecimal balanceAfter;
    private LocalDateTime date;
    private String description;
    private String status;
    private String referenceNumber;
    private String failureReason;
}