package com.tss.banking.service.impl;

<<<<<<< HEAD
import java.time.LocalDate;
import java.util.List;

=======
import java.util.List;
import java.util.stream.Collectors;

import com.tss.banking.entity.enums.TransactionType;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
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
<<<<<<< HEAD
    private ModelMapper mapper;

    @Override
    public FeeRuleResponseDto createFeeRule(FeeRuleRequestDto dto) {
        FeeRule entity = mapper.map(dto, FeeRule.class);
        if (entity.getEffectiveFrom() == null) {
            entity.setEffectiveFrom(LocalDate.now());
        }
        FeeRule saved = feeRuleRepository.save(entity);
        return mapper.map(saved, FeeRuleResponseDto.class);
=======
    private ModelMapper modelMapper;

    @Override
    public FeeRuleResponseDto createFeeRule(FeeRuleRequestDto dto) {
        FeeRule feeRule = modelMapper.map(dto, FeeRule.class);
        FeeRule savedFeeRule = feeRuleRepository.save(feeRule);
        return modelMapper.map(savedFeeRule, FeeRuleResponseDto.class);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @Override
    public FeeRuleResponseDto getFeeRuleById(Long id) {
        FeeRule feeRule = feeRuleRepository.findById(id)
<<<<<<< HEAD
                .orElseThrow(() -> new BankApiException("Fee rule not found with ID: " + id));
        return mapper.map(feeRule, FeeRuleResponseDto.class);
=======
                .orElseThrow(() -> new BankApiException("Fee rule not found with id: " + id));
        return modelMapper.map(feeRule, FeeRuleResponseDto.class);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @Override
    public List<FeeRuleResponseDto> getAllFeeRules() {
<<<<<<< HEAD
        return feeRuleRepository.findAll().stream().map(fr -> mapper.map(fr, FeeRuleResponseDto.class)).toList();
=======
        return feeRuleRepository.findAll().stream()
                .map(feeRule -> modelMapper.map(feeRule, FeeRuleResponseDto.class))
                .collect(Collectors.toList());
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @Override
    public List<FeeRuleResponseDto> getActiveFeeRules() {
<<<<<<< HEAD
        return feeRuleRepository.findByEffectiveToIsNullOrEffectiveToAfter(LocalDate.now())
                .stream().map(fr -> mapper.map(fr, FeeRuleResponseDto.class)).toList();
=======
        return feeRuleRepository.findAllActive().stream()
                .map(feeRule -> modelMapper.map(feeRule, FeeRuleResponseDto.class))
                .collect(Collectors.toList());
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @Override
    public List<FeeRuleResponseDto> getFeeRulesByTransactionType(String transactionType) {
<<<<<<< HEAD
        return feeRuleRepository.findByFeeTypeIgnoreCase(transactionType).stream()
                .map(fr -> mapper.map(fr, FeeRuleResponseDto.class)).toList();
=======
        return feeRuleRepository.findActiveByTransactionType(TransactionType.valueOf(transactionType)).stream()
                .map(feeRule -> modelMapper.map(feeRule, FeeRuleResponseDto.class))
                .collect(Collectors.toList());
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @Override
    public FeeRuleResponseDto updateFeeRule(Long id, FeeRuleRequestDto dto) {
<<<<<<< HEAD
        FeeRule existing = feeRuleRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Fee rule not found with ID: " + id));
        mapper.map(dto, existing);
        FeeRule saved = feeRuleRepository.save(existing);
        return mapper.map(saved, FeeRuleResponseDto.class);
=======
        FeeRule feeRule = feeRuleRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Fee rule not found with id: " + id));
        
        modelMapper.map(dto, feeRule);
        FeeRule updatedFeeRule = feeRuleRepository.save(feeRule);
        return modelMapper.map(updatedFeeRule, FeeRuleResponseDto.class);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @Override
    public void deleteFeeRule(Long id) {
        if (!feeRuleRepository.existsById(id)) {
<<<<<<< HEAD
            throw new BankApiException("Fee rule not found with ID: " + id);
=======
            throw new BankApiException("Fee rule not found with id: " + id);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
        }
        feeRuleRepository.deleteById(id);
    }
}
<<<<<<< HEAD


=======
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
