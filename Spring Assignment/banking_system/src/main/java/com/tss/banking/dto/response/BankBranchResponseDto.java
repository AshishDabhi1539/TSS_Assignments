package com.tss.banking.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankBranchResponseDto {
    private BankInfoDto bank;
    private List<BranchInfoDto> branches;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BankInfoDto {
        private Long id;
        private String name;
        private String code;
        private String currency;
        private String country;
        private String headOfficeAddress;
        private int totalBranches;
        private LocalDateTime establishedDate;
    }
    
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
        private String email;
        private boolean isActive;
        private boolean isMainBranch;
    }
}
