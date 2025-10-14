package com.tss.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailDto {
    private Long id;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private String status;
    private String currency;
    private BigDecimal interestRate;
    private BigDecimal minBalance;
    private LocalDateTime openedAt;
    private LocalDateTime closedAt;
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private Long branchId;
    private String branchName;
    private String branchCode;
    private String branchIfsc;
}
