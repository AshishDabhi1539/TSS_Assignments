package com.tss.banking.service.impl;

import java.time.LocalDate;
import java.util.List;

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
    private ModelMapper mapper;

    @Override
    public FeeRuleResponseDto createFeeRule(FeeRuleRequestDto dto) {
        FeeRule entity = mapper.map(dto, FeeRule.class);
        if (entity.getEffectiveFrom() == null) {
            entity.setEffectiveFrom(LocalDate.now());
        }
        FeeRule saved = feeRuleRepository.save(entity);
        return mapper.map(saved, FeeRuleResponseDto.class);
    }

    @Override
    public FeeRuleResponseDto getFeeRuleById(Long id) {
        FeeRule feeRule = feeRuleRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Fee rule not found with ID: " + id));
        return mapper.map(feeRule, FeeRuleResponseDto.class);
    }

    @Override
    public List<FeeRuleResponseDto> getAllFeeRules() {
        return feeRuleRepository.findAll().stream().map(fr -> mapper.map(fr, FeeRuleResponseDto.class)).toList();
    }

    @Override
    public List<FeeRuleResponseDto> getActiveFeeRules() {
        return feeRuleRepository.findByEffectiveToIsNullOrEffectiveToAfter(LocalDate.now())
                .stream().map(fr -> mapper.map(fr, FeeRuleResponseDto.class)).toList();
    }

    @Override
    public List<FeeRuleResponseDto> getFeeRulesByTransactionType(String transactionType) {
        return feeRuleRepository.findByFeeTypeIgnoreCase(transactionType).stream()
                .map(fr -> mapper.map(fr, FeeRuleResponseDto.class)).toList();
    }

    @Override
    public FeeRuleResponseDto updateFeeRule(Long id, FeeRuleRequestDto dto) {
        FeeRule existing = feeRuleRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Fee rule not found with ID: " + id));
        mapper.map(dto, existing);
        FeeRule saved = feeRuleRepository.save(existing);
        return mapper.map(saved, FeeRuleResponseDto.class);
    }

    @Override
    public void deleteFeeRule(Long id) {
        if (!feeRuleRepository.existsById(id)) {
            throw new BankApiException("Fee rule not found with ID: " + id);
        }
        feeRuleRepository.deleteById(id);
    }
}


