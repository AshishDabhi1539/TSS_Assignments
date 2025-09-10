package com.tss.banking.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.banking.dto.request.BankRequestDto;
import com.tss.banking.dto.response.BankResponseDto;
import com.tss.banking.entity.Bank;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.BankRepository;
import com.tss.banking.service.BankService;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public BankResponseDto createBank(BankRequestDto dto) {
        Bank bank = mapper.map(dto, Bank.class);
        Bank savedBank = bankRepo.save(bank);
        return mapper.map(savedBank, BankResponseDto.class);
    }

    @Override
    public BankResponseDto getBankById(Long id) {
        Bank bank = bankRepo.findById(id)
                .orElseThrow(() -> new BankApiException("Bank not found with ID: " + id));
        return mapper.map(bank, BankResponseDto.class);
    }

    @Override
    public List<BankResponseDto> getAllBanks() {
        List<Bank> banks = bankRepo.findAll();
        return banks.stream()
                .map(bank -> mapper.map(bank, BankResponseDto.class))
                .toList();
    }
}