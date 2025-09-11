package com.tss.banking.service;

import java.util.List;

import com.tss.banking.dto.request.BeneficiaryRequestDto;
import com.tss.banking.dto.response.BeneficiaryResponseDto;

public interface BeneficiaryService {
    
    BeneficiaryResponseDto createBeneficiary(BeneficiaryRequestDto dto);
    
    BeneficiaryResponseDto getBeneficiaryById(Long id);
    
    List<BeneficiaryResponseDto> getAllBeneficiaries();
    
    List<BeneficiaryResponseDto> getBeneficiariesByOwner(Long ownerId);
    
    BeneficiaryResponseDto updateBeneficiary(Long id, BeneficiaryRequestDto dto);
    
    void deleteBeneficiary(Long id);
}
