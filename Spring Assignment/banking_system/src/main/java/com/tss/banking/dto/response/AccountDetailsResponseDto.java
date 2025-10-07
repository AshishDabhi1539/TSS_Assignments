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
public class AccountDetailsResponseDto {
    private Long id;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private BigDecimal availableBalance;
    private String status;
    private String currency;
    private BigDecimal interestRate;
    private BigDecimal minBalance;
    private LocalDateTime openedAt;
    
    // Customer info
    private String customerName;
    private String customerEmail;
    
    // Branch info
    private BranchInfoDto branch;
    
    // Recent transactions (last 5)
    private List<TransactionSummaryDto> recentTransactions;
    
    // Account statistics
    private AccountStatsDto statistics;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BranchInfoDto {
        private Long id;
        private String name;
        private String code;
        private String ifsc;
        private String address;
        private String contactNumber;
        private String bankName;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransactionSummaryDto {
        private Long id;
        private String type;
        private BigDecimal amount;
        private LocalDateTime date;
        private String description;
        private String status;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AccountStatsDto {
        private int totalTransactions;
        private BigDecimal totalCredits;
        private BigDecimal totalDebits;
        private LocalDateTime lastTransactionDate;
    }
}
