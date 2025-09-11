package com.tss.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.banking.dto.request.BeneficiaryRequestDto;
import com.tss.banking.dto.response.BeneficiaryResponseDto;
import com.tss.banking.entity.Account;
import com.tss.banking.entity.Beneficiary;
import com.tss.banking.entity.User;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.AccountRepository;
import com.tss.banking.repository.BeneficiaryRepository;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.BeneficiaryService;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public BeneficiaryResponseDto createBeneficiary(BeneficiaryRequestDto dto) {
        User owner = userRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new BankApiException("Owner not found with id: " + dto.getOwnerId()));

        Account beneficiaryAccount = accountRepository.findById(dto.getBeneficiaryAccountId())
                .orElseThrow(() -> new BankApiException("Beneficiary account not found with id: " + dto.getBeneficiaryAccountId()));

        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setOwner(owner);
        beneficiary.setBeneficiaryAccount(beneficiaryAccount);
        beneficiary.setAlias(dto.getAlias());
        beneficiary.setVerificationStatus(dto.getVerificationStatus() != null ? dto.getVerificationStatus() : "PENDING");

        Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);
        return mapToResponseDto(savedBeneficiary);
    }

    @Override
    public BeneficiaryResponseDto getBeneficiaryById(Long id) {
        Beneficiary beneficiary = beneficiaryRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Beneficiary not found with id: " + id));
        return mapToResponseDto(beneficiary);
    }

    @Override
    public List<BeneficiaryResponseDto> getAllBeneficiaries() {
        return beneficiaryRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BeneficiaryResponseDto> getBeneficiariesByOwner(Long ownerId) {
        return beneficiaryRepository.findByOwnerId(ownerId).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public BeneficiaryResponseDto updateBeneficiary(Long id, BeneficiaryRequestDto dto) {
        Beneficiary beneficiary = beneficiaryRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Beneficiary not found with id: " + id));

        if (dto.getBeneficiaryAccountId() != null) {
            Account beneficiaryAccount = accountRepository.findById(dto.getBeneficiaryAccountId())
                    .orElseThrow(() -> new BankApiException("Beneficiary account not found with id: " + dto.getBeneficiaryAccountId()));
            beneficiary.setBeneficiaryAccount(beneficiaryAccount);
        }

        if (dto.getAlias() != null) {
            beneficiary.setAlias(dto.getAlias());
        }

        if (dto.getVerificationStatus() != null) {
            beneficiary.setVerificationStatus(dto.getVerificationStatus());
        }

        Beneficiary updatedBeneficiary = beneficiaryRepository.save(beneficiary);
        return mapToResponseDto(updatedBeneficiary);
    }

    @Override
    public void deleteBeneficiary(Long id) {
        if (!beneficiaryRepository.existsById(id)) {
            throw new BankApiException("Beneficiary not found with id: " + id);
        }
        beneficiaryRepository.deleteById(id);
    }

    private BeneficiaryResponseDto mapToResponseDto(Beneficiary beneficiary) {
        BeneficiaryResponseDto dto = new BeneficiaryResponseDto();
        dto.setId(beneficiary.getId());
        dto.setOwnerId(beneficiary.getOwner().getId());
        dto.setOwnerName(beneficiary.getOwner().getFirstName() + " " + beneficiary.getOwner().getLastName());
        dto.setBeneficiaryAccountId(beneficiary.getBeneficiaryAccount().getId());
        dto.setBeneficiaryAccountNumber(beneficiary.getBeneficiaryAccount().getAccountNumber());
        dto.setAlias(beneficiary.getAlias());
        dto.setVerificationStatus(beneficiary.getVerificationStatus());
        dto.setCreatedAt(beneficiary.getCreatedAt());
        return dto;
    }
}
