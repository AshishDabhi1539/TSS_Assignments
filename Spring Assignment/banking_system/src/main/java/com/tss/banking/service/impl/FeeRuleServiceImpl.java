package com.tss.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.banking.dto.request.FeeRuleRequestDto;
import com.tss.banking.dto.response.FeeRuleResponseDto;
import com.tss.banking.entity.FeeRule;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.FeeRuleRepository;
import com.tss.banking.service.FeeRuleService;

@Service
public class FeeRuleServiceImpl implements FeeRuleService {

    @Autowired
    private FeeRuleRepository feeRuleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FeeRuleResponseDto createFeeRule(FeeRuleRequestDto dto) {
        FeeRule feeRule = modelMapper.map(dto, FeeRule.class);
        FeeRule savedFeeRule = feeRuleRepository.save(feeRule);
        return modelMapper.map(savedFeeRule, FeeRuleResponseDto.class);
    }

    @Override
    public FeeRuleResponseDto getFeeRuleById(Long id) {
        FeeRule feeRule = feeRuleRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Fee rule not found with id: " + id));
        return modelMapper.map(feeRule, FeeRuleResponseDto.class);
    }

    @Override
    public List<FeeRuleResponseDto> getAllFeeRules() {
        return feeRuleRepository.findAll().stream()
                .map(feeRule -> modelMapper.map(feeRule, FeeRuleResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<FeeRuleResponseDto> getActiveFeeRules() {
        return feeRuleRepository.findAllActive().stream()
                .map(feeRule -> modelMapper.map(feeRule, FeeRuleResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<FeeRuleResponseDto> getFeeRulesByTransactionType(String transactionType) {
        return feeRuleRepository.findActiveByTransactionType(transactionType).stream()
                .map(feeRule -> modelMapper.map(feeRule, FeeRuleResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FeeRuleResponseDto updateFeeRule(Long id, FeeRuleRequestDto dto) {
        FeeRule feeRule = feeRuleRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Fee rule not found with id: " + id));
        
        modelMapper.map(dto, feeRule);
        FeeRule updatedFeeRule = feeRuleRepository.save(feeRule);
        return modelMapper.map(updatedFeeRule, FeeRuleResponseDto.class);
    }

    @Override
    public void deleteFeeRule(Long id) {
        if (!feeRuleRepository.existsById(id)) {
            throw new BankApiException("Fee rule not found with id: " + id);
        }
        feeRuleRepository.deleteById(id);
    }
}
