package com.tss.banking.service;

import java.util.List;

import com.tss.banking.dto.request.FeeRuleRequestDto;
import com.tss.banking.dto.response.FeeRuleResponseDto;

public interface FeeRuleService {
    FeeRuleResponseDto createFeeRule(FeeRuleRequestDto dto);
    FeeRuleResponseDto getFeeRuleById(Long id);
    List<FeeRuleResponseDto> getAllFeeRules();
    List<FeeRuleResponseDto> getActiveFeeRules();
    List<FeeRuleResponseDto> getFeeRulesByTransactionType(String transactionType);
    FeeRuleResponseDto updateFeeRule(Long id, FeeRuleRequestDto dto);
    void deleteFeeRule(Long id);
}
