package com.tss.banking.service;

import java.util.List;

import com.tss.banking.dto.request.FeeRuleRequestDto;
import com.tss.banking.dto.response.FeeRuleResponseDto;

public interface FeeRuleService {
<<<<<<< HEAD
    FeeRuleResponseDto createFeeRule(FeeRuleRequestDto dto);
    FeeRuleResponseDto getFeeRuleById(Long id);
    List<FeeRuleResponseDto> getAllFeeRules();
    List<FeeRuleResponseDto> getActiveFeeRules();
    List<FeeRuleResponseDto> getFeeRulesByTransactionType(String transactionType);
    FeeRuleResponseDto updateFeeRule(Long id, FeeRuleRequestDto dto);
=======
    
    FeeRuleResponseDto createFeeRule(FeeRuleRequestDto dto);
    
    FeeRuleResponseDto getFeeRuleById(Long id);
    
    List<FeeRuleResponseDto> getAllFeeRules();
    
    List<FeeRuleResponseDto> getActiveFeeRules();
    
    List<FeeRuleResponseDto> getFeeRulesByTransactionType(String transactionType);
    
    FeeRuleResponseDto updateFeeRule(Long id, FeeRuleRequestDto dto);
    
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    void deleteFeeRule(Long id);
}
