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
    private Long ownerId;
    private String ownerName;
    private Long beneficiaryAccountId;
    private String beneficiaryAccountNumber;
    private String alias;
    private String verificationStatus;
    private LocalDateTime createdAt;
}
