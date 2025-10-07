package com.tss.banking.service;

import java.util.List;

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
}
