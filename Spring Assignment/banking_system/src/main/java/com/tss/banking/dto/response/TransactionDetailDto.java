package com.tss.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetailDto {
    private Long id;
    private String type;
    private BigDecimal amount;
    private BigDecimal feeAmount;
    private String fromAccountNumber;
    private String toAccountNumber;
    private String description;
    private String status;
    private LocalDateTime date;
    private String referenceNumber;
    private String idempotencyKey;
    private BigDecimal balanceBefore;
    private BigDecimal balanceAfter;
    private String failureReason;
    private LocalDateTime updatedAt;
}
