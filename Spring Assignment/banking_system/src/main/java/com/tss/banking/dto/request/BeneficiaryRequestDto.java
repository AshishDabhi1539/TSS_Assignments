package com.tss.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
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
    
    @NotBlank(message = "Alias is required")
    @Size(min = 2, max = 64, message = "Alias must be between 2 and 64 characters")
    private String alias;
    
    private com.tss.banking.entity.enums.VerificationStatus verificationStatus = com.tss.banking.entity.enums.VerificationStatus.PENDING;
}
