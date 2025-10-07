package com.tss.banking.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String phone;
    private String status;
    private String role;
    private boolean emailVerified;
    private LocalDateTime joinedDate;
    
    // Address information
    private List<AddressResponseDto> addresses;
    
    // Account summary
    private int totalAccounts;
    private List<AccountSummaryDto> accounts;
    
    // KYC status
    private String kycStatus;
    private boolean kycCompleted;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AccountSummaryDto {
        private Long id;
        private String accountNumber;
        private String accountType;
        private String status;
        private String branchName;
        private String branchCode;
    }
}
