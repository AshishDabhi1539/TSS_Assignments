package com.tss.banking.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryRequestDto {
    
    @NotNull(message = "Owner ID is required")
    private Long ownerId;
    
    @NotNull(message = "Beneficiary account ID is required")
    private Long beneficiaryAccountId;
    
    @Size(max = 64, message = "Alias must not exceed 64 characters")
    private String alias;
    
    @Size(max = 32, message = "Verification status must not exceed 32 characters")
    private String verificationStatus;
}
