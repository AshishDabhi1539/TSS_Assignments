package com.tss.banking.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.banking.dto.request.BankRequestDto;
import com.tss.banking.dto.response.BankResponseDto;
import com.tss.banking.entity.Bank;
import com.tss.banking.entity.Branch;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.BankRepository;
import com.tss.banking.repository.BranchRepository;
import com.tss.banking.service.BankService;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BranchRepository branchRepo;

    @Override
    public BankResponseDto createBank(BankRequestDto dto) {
        // Enforce single-bank mode
        if (bankRepo.count() > 0) {
            throw new BankApiException("Bank already exists. Single-bank mode enabled.");
        }

        Bank bank = mapper.map(dto, Bank.class);
        if (bank.getCurrency() == null || bank.getCurrency().isBlank()) {
            bank.setCurrency("INR");
        }
        if (bank.getCode() == null || bank.getCode().isBlank()) {
            bank.setCode(generateBankCode());
        }

        Bank savedBank = bankRepo.save(bank);

        // Auto-create default branch
        Branch defaultBranch = new Branch();
        defaultBranch.setName(dto.getDefaultBranchName() != null ? dto.getDefaultBranchName() : "Head Office");
        defaultBranch.setCode(dto.getDefaultBranchCode() != null ? dto.getDefaultBranchCode() : generateBranchCode("HO"));
        defaultBranch.setIfsc(dto.getDefaultBranchIfsc() != null ? dto.getDefaultBranchIfsc() : generateIfsc(savedBank.getCode(), "0001"));
        defaultBranch.setAddress(savedBank.getAddress());
        defaultBranch.setContactNumber(null);
        defaultBranch.setBank(savedBank);
        branchRepo.save(defaultBranch);

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

    @Override
    public boolean bankExists() {
        return bankRepo.count() > 0;
    }

    private String generateBankCode() {
        return "BANK-0001"; // simple placeholder
    }

    private String generateBranchCode(String prefix) {
        return prefix + "-0001"; // simple placeholder
    }

    private String generateIfsc(String bankCode, String seq) {
        // Example IFSC format: BKID0001HO or BANK0001HO; keeping simple
        String base = bankCode.replaceAll("[^A-Z0-9]", "");
        if (base.length() < 4) {
            base = (base + "XXXX").substring(0, 4);
        }
        return base + seq + "HO";
    }
}