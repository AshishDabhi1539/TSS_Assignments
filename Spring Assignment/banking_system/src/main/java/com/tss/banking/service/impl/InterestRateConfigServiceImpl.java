package com.tss.banking.service.impl;

<<<<<<< HEAD
import java.time.LocalDate;
import java.util.List;
=======
import java.util.List;
import java.util.stream.Collectors;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba

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
<<<<<<< HEAD
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
=======
    private InterestRateConfigRepository interestRateConfigRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public InterestRateConfigResponseDto createInterestRateConfig(InterestRateConfigRequestDto dto) {
        InterestRateConfig config = modelMapper.map(dto, InterestRateConfig.class);
        InterestRateConfig savedConfig = interestRateConfigRepository.save(config);
        return modelMapper.map(savedConfig, InterestRateConfigResponseDto.class);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @Override
    public InterestRateConfigResponseDto getInterestRateConfigById(Long id) {
<<<<<<< HEAD
        InterestRateConfig entity = repo.findById(id)
                .orElseThrow(() -> new BankApiException("Interest rate config not found with ID: " + id));
        return mapper.map(entity, InterestRateConfigResponseDto.class);
=======
        InterestRateConfig config = interestRateConfigRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Interest rate config not found with id: " + id));
        return modelMapper.map(config, InterestRateConfigResponseDto.class);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @Override
    public List<InterestRateConfigResponseDto> getAllInterestRateConfigs() {
<<<<<<< HEAD
        return repo.findAll().stream().map(e -> mapper.map(e, InterestRateConfigResponseDto.class)).toList();
=======
        return interestRateConfigRepository.findAll().stream()
                .map(config -> modelMapper.map(config, InterestRateConfigResponseDto.class))
                .collect(Collectors.toList());
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @Override
    public List<InterestRateConfigResponseDto> getActiveInterestRateConfigs() {
<<<<<<< HEAD
        return repo.findByEffectiveToIsNullOrEffectiveToAfter(LocalDate.now())
                .stream().map(e -> mapper.map(e, InterestRateConfigResponseDto.class)).toList();
=======
        return interestRateConfigRepository.findAllActive().stream()
                .map(config -> modelMapper.map(config, InterestRateConfigResponseDto.class))
                .collect(Collectors.toList());
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @Override
    public List<InterestRateConfigResponseDto> getInterestRateConfigsByAccountType(String accountType) {
<<<<<<< HEAD
        // For simplicity, filter in-memory based on enum name string until a method is added to repository
        String normalized = accountType == null ? "" : accountType.trim().toUpperCase();
        return repo.findAll().stream()
                .filter(e -> e.getAccountType() != null && e.getAccountType().name().equalsIgnoreCase(normalized))
                .map(e -> mapper.map(e, InterestRateConfigResponseDto.class))
                .toList();
=======
        return interestRateConfigRepository.findActiveByAccountType(accountType).stream()
                .map(config -> modelMapper.map(config, InterestRateConfigResponseDto.class))
                .collect(Collectors.toList());
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @Override
    public InterestRateConfigResponseDto updateInterestRateConfig(Long id, InterestRateConfigRequestDto dto) {
<<<<<<< HEAD
        InterestRateConfig existing = repo.findById(id)
                .orElseThrow(() -> new BankApiException("Interest rate config not found with ID: " + id));
        mapper.map(dto, existing);
        InterestRateConfig saved = repo.save(existing);
        return mapper.map(saved, InterestRateConfigResponseDto.class);
=======
        InterestRateConfig config = interestRateConfigRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Interest rate config not found with id: " + id));
        
        modelMapper.map(dto, config);
        InterestRateConfig updatedConfig = interestRateConfigRepository.save(config);
        return modelMapper.map(updatedConfig, InterestRateConfigResponseDto.class);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @Override
    public void deleteInterestRateConfig(Long id) {
<<<<<<< HEAD
        if (!repo.existsById(id)) {
            throw new BankApiException("Interest rate config not found with ID: " + id);
        }
        repo.deleteById(id);
    }
}


=======
        if (!interestRateConfigRepository.existsById(id)) {
            throw new BankApiException("Interest rate config not found with id: " + id);
        }
        interestRateConfigRepository.deleteById(id);
    }
}
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
