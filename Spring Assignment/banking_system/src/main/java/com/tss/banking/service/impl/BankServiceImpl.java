package com.tss.banking.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tss.banking.dto.request.BankRequestDto;
import com.tss.banking.dto.response.BankResponseDto;
import com.tss.banking.entity.Bank;
import com.tss.banking.entity.Branch;
import com.tss.banking.entity.enums.AddressType;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.BankRepository;
import com.tss.banking.repository.BranchRepository;
import com.tss.banking.service.AddressService;
import com.tss.banking.service.BankService;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BranchRepository branchRepo;

    @Autowired
    private AddressService addressService;

    @Override
    @Transactional
    public BankResponseDto createBank(BankRequestDto dto) {
        try {
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

            // Create address for the bank using unified address system
            createBankAddress(savedBank.getId(), dto);

            // Auto-create default branch with required contact details
            Branch defaultBranch = new Branch();
            defaultBranch.setName(dto.getDefaultBranchName() != null ? dto.getDefaultBranchName() : "Head Office");
            defaultBranch.setCode(generateBranchCode(savedBank.getCode(), "001"));
            defaultBranch.setIfsc(generateIfsc(savedBank.getCode(), "0001"));
            defaultBranch.setBank(savedBank);
            defaultBranch.setIsActive(true);
            // Set default contact details for head office branch
            defaultBranch.setContactNumber("+911234567890");
            defaultBranch.setEmail("headoffice@" + savedBank.getName().toLowerCase().replaceAll("\\s+", "") + ".com");
            Branch savedBranch = branchRepo.save(defaultBranch);

            // Create address for the default branch using the same bank address
            createBranchAddress(savedBranch.getId(), dto);

            return mapper.map(savedBank, BankResponseDto.class);
        } catch (Exception e) {
            throw new BankApiException("Failed to create bank: " + e.getMessage());
        }
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

    private String generateBranchCode(String bankCode, String sequence) {
        // Generate consistent branch code: BANKCODE + sequence
        String cleanBankCode = bankCode.replaceAll("[^A-Z0-9]", "");
        if (cleanBankCode.length() > 4) {
            cleanBankCode = cleanBankCode.substring(0, 4);
        }
        return cleanBankCode + sequence;
    }

    private String generateIfsc(String bankCode, String seq) {
        // Example IFSC format: BKID0001HO or BANK0001HO; keeping simple
        String base = bankCode.replaceAll("[^A-Z0-9]", "");
        if (base.length() < 4) {
            base = (base + "XXXX").substring(0, 4);
        }
        return base + seq + "HO";
    }

    private void createBankAddress(Long bankId, BankRequestDto dto) {
        try {
            addressService.createAddressForEntity("BANK", bankId, AddressType.HEAD_OFFICE,
                    dto.getAddressLine1(), dto.getAddressLine2(), dto.getCity(),
                    dto.getState(), dto.getPostalCode(), dto.getCountry(), "Corporate Office");
        } catch (Exception e) {
            System.err.println("Failed to create address for bank: " + e.getMessage());
        }
    }

    private void createBranchAddress(Long branchId, BankRequestDto dto) {
        try {
            addressService.createAddressForEntity("BRANCH", branchId, AddressType.BRANCH,
                    dto.getAddressLine1(), dto.getAddressLine2(), dto.getCity(),
                    dto.getState(), dto.getPostalCode(), dto.getCountry(), "Near Main Road");
        } catch (Exception e) {
            System.err.println("Failed to create address for branch: " + e.getMessage());
        }
    }
}