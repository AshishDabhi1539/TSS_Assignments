package com.tss.banking.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountStatementDto {
    private Long accountId;
    private String accountNumber;
    private String accountType;
    private String customerName;
    private String customerEmail;
    private BigDecimal currentBalance;
    private LocalDateTime statementGeneratedAt;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private List<TransactionResponseDto> transactions;
    private BigDecimal totalCredits;
    private BigDecimal totalDebits;
    private int transactionCount;
}
