package com.tss.banking.service;

import java.util.List;

import com.tss.banking.dto.request.InterestRateConfigRequestDto;
import com.tss.banking.dto.response.InterestRateConfigResponseDto;

public interface InterestRateConfigService {
    InterestRateConfigResponseDto createInterestRateConfig(InterestRateConfigRequestDto dto);
    InterestRateConfigResponseDto getInterestRateConfigById(Long id);
    List<InterestRateConfigResponseDto> getAllInterestRateConfigs();
    List<InterestRateConfigResponseDto> getActiveInterestRateConfigs();
    List<InterestRateConfigResponseDto> getInterestRateConfigsByAccountType(String accountType);
    InterestRateConfigResponseDto updateInterestRateConfig(Long id, InterestRateConfigRequestDto dto);
    void deleteInterestRateConfig(Long id);
}


