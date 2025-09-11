package com.tss.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.banking.dto.request.InterestRateConfigRequestDto;
import com.tss.banking.dto.response.InterestRateConfigResponseDto;
import com.tss.banking.entity.InterestRateConfig;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.InterestRateConfigRepository;
import com.tss.banking.service.InterestRateConfigService;

@Service
public class InterestRateConfigServiceImpl implements InterestRateConfigService {

    @Autowired
    private InterestRateConfigRepository interestRateConfigRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public InterestRateConfigResponseDto createInterestRateConfig(InterestRateConfigRequestDto dto) {
        InterestRateConfig config = modelMapper.map(dto, InterestRateConfig.class);
        InterestRateConfig savedConfig = interestRateConfigRepository.save(config);
        return modelMapper.map(savedConfig, InterestRateConfigResponseDto.class);
    }

    @Override
    public InterestRateConfigResponseDto getInterestRateConfigById(Long id) {
        InterestRateConfig config = interestRateConfigRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Interest rate config not found with id: " + id));
        return modelMapper.map(config, InterestRateConfigResponseDto.class);
    }

    @Override
    public List<InterestRateConfigResponseDto> getAllInterestRateConfigs() {
        return interestRateConfigRepository.findAll().stream()
                .map(config -> modelMapper.map(config, InterestRateConfigResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<InterestRateConfigResponseDto> getActiveInterestRateConfigs() {
        return interestRateConfigRepository.findAllActive().stream()
                .map(config -> modelMapper.map(config, InterestRateConfigResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<InterestRateConfigResponseDto> getInterestRateConfigsByAccountType(String accountType) {
        return interestRateConfigRepository.findActiveByAccountType(accountType).stream()
                .map(config -> modelMapper.map(config, InterestRateConfigResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public InterestRateConfigResponseDto updateInterestRateConfig(Long id, InterestRateConfigRequestDto dto) {
        InterestRateConfig config = interestRateConfigRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Interest rate config not found with id: " + id));
        
        modelMapper.map(dto, config);
        InterestRateConfig updatedConfig = interestRateConfigRepository.save(config);
        return modelMapper.map(updatedConfig, InterestRateConfigResponseDto.class);
    }

    @Override
    public void deleteInterestRateConfig(Long id) {
        if (!interestRateConfigRepository.existsById(id)) {
            throw new BankApiException("Interest rate config not found with id: " + id);
        }
        interestRateConfigRepository.deleteById(id);
    }
}
