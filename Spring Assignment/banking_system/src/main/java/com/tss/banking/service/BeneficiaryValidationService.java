package com.tss.banking.service;

import com.tss.banking.dto.request.BeneficiaryValidationRequestDto;
import com.tss.banking.dto.response.BeneficiaryValidationResponseDto;

public interface BeneficiaryValidationService {
    
    /**
     * Validate beneficiary details comprehensively
     */
    BeneficiaryValidationResponseDto validateBeneficiary(BeneficiaryValidationRequestDto request, Long customerId);
    
    /**
     * Validate account number format
     */
    boolean isValidAccountNumber(String accountNumber);
    
    /**
     * Validate IFSC code format
     */
    boolean isValidIfscCode(String ifscCode);
    
    /**
     * Check if account exists in the system
     */
    boolean accountExists(String accountNumber);
    
    /**
     * Check if IFSC code exists in the system
     */
    boolean ifscCodeExists(String ifscCode);
    
    /**
     * Validate account holder name
     */
    boolean isValidAccountHolderName(String name);
    
    /**
     * Check if beneficiary already exists for customer
     */
    boolean beneficiaryExists(Long customerId, String accountNumber);
    
    /**
     * Check if customer is trying to add their own account as beneficiary
     */
    boolean isSelfAccount(Long customerId, String accountNumber);
}
