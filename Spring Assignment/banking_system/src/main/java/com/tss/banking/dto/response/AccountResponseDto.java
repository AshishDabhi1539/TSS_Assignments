package com.tss.banking.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {
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
    
    // Customer info (no sensitive data)
    private Long customerId;
    private String customerName;
    
    // Branch info
    private Long branchId;
    private String branchName;
    private String branchCode;
}