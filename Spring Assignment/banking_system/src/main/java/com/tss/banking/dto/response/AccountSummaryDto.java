package com.tss.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountSummaryDto {
    private Long id;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private String status;
    private String currency;
    private BigDecimal minBalance;
    private LocalDateTime openedAt;
    private String branchName;
    private String branchCode;
}
