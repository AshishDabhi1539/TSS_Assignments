package com.tss.banking.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryResponseDto {
    private Long id;
    private String alias;
    private String beneficiaryAccountNumber;
    private String accountHolderName;
    private String bankName;
    private String branchName;
    private String ifsc;
    private String verificationStatus;
    private LocalDateTime createdAt;
    private boolean isActive;
}
