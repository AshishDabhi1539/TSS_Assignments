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
public class DashboardResponseDto {
    private UserSummaryDto userSummary;
    private List<AccountSummaryDto> accounts;
    private List<RecentTransactionDto> recentTransactions;
    private List<NotificationDto> notifications;
    private QuickStatsDto quickStats;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserSummaryDto {
        private String fullName;
        private String email;
        private String role;
        private boolean kycCompleted;
        private LocalDateTime lastLogin;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AccountSummaryDto {
        private Long id;
        private String accountNumber;
        private String accountType;
        private BigDecimal balance;
        private String status;
        private String branchName;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecentTransactionDto {
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
    public static class NotificationDto {
        private Long id;
        private String title;
        private String message;
        private String type;
        private LocalDateTime createdAt;
        private boolean isRead;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuickStatsDto {
        private int totalAccounts;
        private BigDecimal totalBalance;
        private int transactionsThisMonth;
        private int pendingApprovals;
        private int unreadNotifications;
    }
}
