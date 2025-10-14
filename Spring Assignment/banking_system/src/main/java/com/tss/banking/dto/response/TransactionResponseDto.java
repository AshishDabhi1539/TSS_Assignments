package com.tss.banking.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
<<<<<<< HEAD
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
=======
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
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    private LocalDateTime date;
    private String description;
    private String status;
    private String referenceNumber;
<<<<<<< HEAD
=======
    private String failureReason;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
}