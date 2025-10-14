package com.tss.banking.service.impl;

import java.time.LocalDate;
import java.util.List;

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
    private InterestRateConfigRepository repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public InterestRateConfigResponseDto createInterestRateConfig(InterestRateConfigRequestDto dto) {
        InterestRateConfig entity = mapper.map(dto, InterestRateConfig.class);
        if (entity.getEffectiveFrom() == null) {
            entity.setEffectiveFrom(LocalDate.now());
        }
        InterestRateConfig saved = repo.save(entity);
        return mapper.map(saved, InterestRateConfigResponseDto.class);
    }

    @Override
    public InterestRateConfigResponseDto getInterestRateConfigById(Long id) {
        InterestRateConfig entity = repo.findById(id)
                .orElseThrow(() -> new BankApiException("Interest rate config not found with ID: " + id));
        return mapper.map(entity, InterestRateConfigResponseDto.class);
    }

    @Override
    public List<InterestRateConfigResponseDto> getAllInterestRateConfigs() {
        return repo.findAll().stream().map(e -> mapper.map(e, InterestRateConfigResponseDto.class)).toList();
    }

    @Override
    public List<InterestRateConfigResponseDto> getActiveInterestRateConfigs() {
        return repo.findByEffectiveToIsNullOrEffectiveToAfter(LocalDate.now())
                .stream().map(e -> mapper.map(e, InterestRateConfigResponseDto.class)).toList();
    }

    @Override
    public List<InterestRateConfigResponseDto> getInterestRateConfigsByAccountType(String accountType) {
        // For simplicity, filter in-memory based on enum name string until a method is added to repository
        String normalized = accountType == null ? "" : accountType.trim().toUpperCase();
        return repo.findAll().stream()
                .filter(e -> e.getAccountType() != null && e.getAccountType().name().equalsIgnoreCase(normalized))
                .map(e -> mapper.map(e, InterestRateConfigResponseDto.class))
                .toList();
    }

    @Override
    public InterestRateConfigResponseDto updateInterestRateConfig(Long id, InterestRateConfigRequestDto dto) {
        InterestRateConfig existing = repo.findById(id)
                .orElseThrow(() -> new BankApiException("Interest rate config not found with ID: " + id));
        mapper.map(dto, existing);
        InterestRateConfig saved = repo.save(existing);
        return mapper.map(saved, InterestRateConfigResponseDto.class);
    }

    @Override
    public void deleteInterestRateConfig(Long id) {
        if (!repo.existsById(id)) {
            throw new BankApiException("Interest rate config not found with ID: " + id);
        }
        repo.deleteById(id);
    }
}


