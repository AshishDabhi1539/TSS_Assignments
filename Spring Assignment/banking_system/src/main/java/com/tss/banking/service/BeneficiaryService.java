package com.tss.banking.service;

import java.util.List;
<<<<<<< HEAD
import java.math.BigDecimal;

import com.tss.banking.dto.request.BeneficiaryActivationRequestDto;
import com.tss.banking.dto.request.BeneficiaryApprovalRequestDto;
import com.tss.banking.dto.request.BeneficiaryCreateRequestDto;
import com.tss.banking.dto.request.BeneficiaryTransferRequestDto;
import com.tss.banking.dto.request.BeneficiaryUpdateRequestDto;
import com.tss.banking.dto.request.BeneficiaryValidationRequestDto;
import com.tss.banking.dto.response.BeneficiaryActivationResponseDto;
import com.tss.banking.dto.response.BeneficiaryResponseDto;
import com.tss.banking.dto.response.BeneficiaryValidationResponseDto;

public interface BeneficiaryService {
    
    // ESSENTIAL CUSTOMER OPERATIONS
    BeneficiaryResponseDto addBeneficiaryForCustomer(BeneficiaryCreateRequestDto dto, String customerEmail);
    BeneficiaryValidationResponseDto validateBeneficiary(BeneficiaryValidationRequestDto request, String customerEmail);
    BeneficiaryResponseDto addBeneficiaryWithValidation(BeneficiaryValidationRequestDto request, String customerEmail);
    
    BeneficiaryActivationResponseDto activateBeneficiary(BeneficiaryActivationRequestDto request, String customerEmail);
    String generateTransferOtp(String customerEmail, Long beneficiaryId, BigDecimal amount);
    BeneficiaryResponseDto transferToBeneficiary(BeneficiaryTransferRequestDto request, String customerEmail);
    
    // CUSTOMER MANAGEMENT
    BeneficiaryResponseDto getBeneficiaryByIdForCustomer(Long id, String customerEmail);
    List<BeneficiaryResponseDto> getBeneficiariesByCustomerEmail(String customerEmail);
    List<BeneficiaryResponseDto> getActiveBeneficiaries(String customerEmail);
    List<BeneficiaryResponseDto> getBeneficiariesByStatus(String status, String customerEmail);
    List<BeneficiaryResponseDto> getBeneficiariesReadyForActivationByCustomer(String customerEmail);
    
    BeneficiaryResponseDto updateBeneficiaryForCustomer(Long id, BeneficiaryUpdateRequestDto dto, String customerEmail);
    void deleteBeneficiaryForCustomer(Long id, String customerEmail);
    
    // ADMIN OPERATIONS
    List<BeneficiaryResponseDto> getAllBeneficiaries();
    List<BeneficiaryResponseDto> getBeneficiariesByApprovalStatus(String approvalStatus);
    List<BeneficiaryResponseDto> getBeneficiariesReadyForActivation();
    List<BeneficiaryResponseDto> getDormantBeneficiaries();
    BeneficiaryResponseDto approveBeneficiary(BeneficiaryApprovalRequestDto request, String approverEmail);
    
    // SYSTEM UTILITIES
    void processCoolingPeriodExpiry();
    void processDormantBeneficiaries();
    void resetDailyTransferLimits();
    void resetMonthlyTransferLimits();
=======

import com.tss.banking.dto.request.BeneficiaryRequestDto;
import com.tss.banking.dto.response.BeneficiaryResponseDto;

public interface BeneficiaryService {
    
    BeneficiaryResponseDto createBeneficiary(BeneficiaryRequestDto dto);
    BeneficiaryResponseDto addBeneficiaryForCustomer(BeneficiaryRequestDto dto, String customerEmail);
    
    BeneficiaryResponseDto getBeneficiaryById(Long id);
    BeneficiaryResponseDto getBeneficiaryByIdForCustomer(Long id, String customerEmail);
    
    List<BeneficiaryResponseDto> getAllBeneficiaries();
    List<BeneficiaryResponseDto> getBeneficiariesByOwner(Long ownerId);
    List<BeneficiaryResponseDto> getBeneficiariesByCustomerEmail(String customerEmail);
    
    BeneficiaryResponseDto updateBeneficiary(Long id, BeneficiaryRequestDto dto);
    BeneficiaryResponseDto updateBeneficiaryForCustomer(Long id, BeneficiaryRequestDto dto, String customerEmail);
    
    void deleteBeneficiary(Long id);
    void deleteBeneficiaryForCustomer(Long id, String customerEmail);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
}
