package com.tss.banking.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryListResponseDto {
    private List<BeneficiaryDetailDto> beneficiaries;
    private int totalBeneficiaries;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BeneficiaryDetailDto {
        private Long id;
        private String alias;
        private String accountNumber;
        private String accountHolderName;
        private String bankName;
        private String branchName;
        private String ifsc;
        private String verificationStatus;
        private LocalDateTime addedDate;
        private boolean isActive;
        
        // For quick transfer reference
        private String displayName; // alias or account holder name
        private String bankDetails; // formatted bank and branch info
    }
}
