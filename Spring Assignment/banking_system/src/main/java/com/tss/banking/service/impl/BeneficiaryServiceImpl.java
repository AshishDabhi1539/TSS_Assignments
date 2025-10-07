package com.tss.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.tss.banking.entity.enums.VerificationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.banking.dto.request.BeneficiaryRequestDto;
import com.tss.banking.dto.response.BeneficiaryResponseDto;
import com.tss.banking.entity.Account;
import com.tss.banking.entity.Beneficiary;
import com.tss.banking.entity.Branch;
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
        beneficiary.setVerificationStatus(dto.getVerificationStatus() != null ? dto.getVerificationStatus() : VerificationStatus.PENDING);

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

    @Override
    public BeneficiaryResponseDto addBeneficiaryForCustomer(BeneficiaryRequestDto dto, String customerEmail) {
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new BankApiException("Customer not found"));

        // Validate that the provided ownerId matches the authenticated user
        if (!customer.getId().equals(dto.getOwnerId())) {
            throw new BankApiException("Owner ID in request does not match authenticated user");
        }

        Account beneficiaryAccount = accountRepository.findById(dto.getBeneficiaryAccountId())
                .orElseThrow(() -> new BankApiException("Beneficiary account not found with id: " + dto.getBeneficiaryAccountId()));

        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setOwner(customer);
        beneficiary.setBeneficiaryAccount(beneficiaryAccount);
        beneficiary.setAlias(dto.getAlias());
        beneficiary.setVerificationStatus(VerificationStatus.PENDING);

        Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);
        return mapToResponseDto(savedBeneficiary);
    }

    @Override
    public BeneficiaryResponseDto getBeneficiaryByIdForCustomer(Long id, String customerEmail) {
        Beneficiary beneficiary = beneficiaryRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Beneficiary not found with id: " + id));
        
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new BankApiException("Customer not found"));
        
        if (!beneficiary.getOwner().getId().equals(customer.getId())) {
            throw new BankApiException("Access denied: You can only view your own beneficiaries");
        }
        
        return mapToResponseDto(beneficiary);
    }

    @Override
    public List<BeneficiaryResponseDto> getBeneficiariesByCustomerEmail(String customerEmail) {
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new BankApiException("Customer not found"));
        
        return beneficiaryRepository.findByOwnerId(customer.getId()).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public BeneficiaryResponseDto updateBeneficiaryForCustomer(Long id, BeneficiaryRequestDto dto, String customerEmail) {
        Beneficiary beneficiary = beneficiaryRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Beneficiary not found with id: " + id));
        
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new BankApiException("Customer not found"));
        
        // Validate that the provided ownerId matches the authenticated user
        if (!customer.getId().equals(dto.getOwnerId())) {
            throw new BankApiException("Owner ID in request does not match authenticated user");
        }
        
        if (!beneficiary.getOwner().getId().equals(customer.getId())) {
            throw new BankApiException("Access denied: You can only update your own beneficiaries");
        }

        if (dto.getBeneficiaryAccountId() != null) {
            Account beneficiaryAccount = accountRepository.findById(dto.getBeneficiaryAccountId())
                    .orElseThrow(() -> new BankApiException("Beneficiary account not found with id: " + dto.getBeneficiaryAccountId()));
            beneficiary.setBeneficiaryAccount(beneficiaryAccount);
        }

        if (dto.getAlias() != null) {
            beneficiary.setAlias(dto.getAlias());
        }

        Beneficiary updatedBeneficiary = beneficiaryRepository.save(beneficiary);
        return mapToResponseDto(updatedBeneficiary);
    }

    @Override
    public void deleteBeneficiaryForCustomer(Long id, String customerEmail) {
        Beneficiary beneficiary = beneficiaryRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Beneficiary not found with id: " + id));
        
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new BankApiException("Customer not found"));
        
        if (!beneficiary.getOwner().getId().equals(customer.getId())) {
            throw new BankApiException("Access denied: You can only delete your own beneficiaries");
        }
        
        beneficiaryRepository.deleteById(id);
    }

    private BeneficiaryResponseDto mapToResponseDto(Beneficiary beneficiary) {
        BeneficiaryResponseDto dto = new BeneficiaryResponseDto();
        dto.setId(beneficiary.getId());
        dto.setAlias(beneficiary.getAlias());
        dto.setBeneficiaryAccountNumber(beneficiary.getBeneficiaryAccount().getAccountNumber());
        
        // Get account holder name from beneficiary account's customer
        User accountHolder = beneficiary.getBeneficiaryAccount().getCustomer();
        dto.setAccountHolderName(accountHolder.getFirstName() + " " + accountHolder.getLastName());
        
        // Get bank and branch information
        Branch branch = beneficiary.getBeneficiaryAccount().getBranch();
        dto.setBranchName(branch.getName());
        dto.setIfsc(branch.getIfsc());
        dto.setBankName(branch.getBank().getName());
        
        dto.setVerificationStatus(beneficiary.getVerificationStatus().toString());
        dto.setCreatedAt(beneficiary.getCreatedAt());
        dto.setActive(true); // Default to active, can be enhanced later
        
        return dto;
    }
}
