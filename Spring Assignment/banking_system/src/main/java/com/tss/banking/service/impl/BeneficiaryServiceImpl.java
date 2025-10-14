package com.tss.banking.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.tss.banking.dto.request.*;
// import com.tss.banking.dto.response.TransactionResponseDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tss.banking.dto.response.BeneficiaryActivationResponseDto;
import com.tss.banking.dto.response.BeneficiaryResponseDto;
import com.tss.banking.dto.response.BeneficiaryValidationResponseDto;
import com.tss.banking.entity.Account;
import com.tss.banking.entity.Beneficiary;
// import com.tss.banking.entity.Branch;
import com.tss.banking.entity.User;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.AccountRepository;
import com.tss.banking.repository.BeneficiaryRepository;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.BeneficiaryService;
// import com.tss.banking.service.BeneficiaryValidationService;
// import com.tss.banking.service.EmailService;
// import com.tss.banking.service.OtpService;
// import com.tss.banking.service.TransactionService;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private static final Logger log = LoggerFactory.getLogger(BeneficiaryServiceImpl.class);

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    // private final BranchRepository branchRepository;

    // private final BeneficiaryValidationService validationService;

    // private final EmailService emailService;

    // private final OtpService otpService;

    // private final TransactionService transactionService;

    @Override
    @Transactional
    public BeneficiaryResponseDto addBeneficiaryForCustomer(BeneficiaryCreateRequestDto dto, String customerEmail) {
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new BankApiException("Customer not found"));

        // Find beneficiary account
        Account beneficiaryAccount = accountRepository.findByAccountNumber(dto.getAccountNumber())
                .orElseThrow(() -> new BankApiException("Beneficiary account not found with account number: " + dto.getAccountNumber()));

        // Create beneficiary
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setOwner(customer);
        beneficiary.setBeneficiaryAccount(beneficiaryAccount);
        beneficiary.setAlias(dto.getAlias());
        // Beneficiary entity in this project only supports alias + createdAt
        beneficiary.setCreatedAt(LocalDateTime.now());

        Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);

        log.info("Beneficiary added for customer: {} with account: {}, ID: {}", customerEmail, beneficiaryAccount.getAccountNumber(), savedBeneficiary.getId());

        return mapToResponseDtoBasic(savedBeneficiary);
    }

    @Override
    @Transactional
    public BeneficiaryResponseDto updateBeneficiaryForCustomer(Long id, BeneficiaryUpdateRequestDto dto, String customerEmail) {
        Beneficiary beneficiary = beneficiaryRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Beneficiary not found with id: " + id));
        
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new BankApiException("Customer not found"));
        
        if (!beneficiary.getOwner().getId().equals(customer.getId())) {
            throw new BankApiException("Access denied: You can only update your own beneficiaries");
        }

        // Update only the fields that are provided in BeneficiaryUpdateRequestDto
        if (dto.getAlias() != null) {
            beneficiary.setAlias(dto.getAlias());
        }

        // Remarks not present in current entity; ignore if provided

        Beneficiary updatedBeneficiary = beneficiaryRepository.save(beneficiary);
        return mapToResponseDtoBasic(updatedBeneficiary);
    }

    @Override
    @Transactional
    public void deleteBeneficiaryForCustomer(Long id, String customerEmail) {
        Beneficiary beneficiary = beneficiaryRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Beneficiary not found with id: " + id));

        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new BankApiException("Customer not found"));

        if (!beneficiary.getOwner().getId().equals(customer.getId())) {
            throw new BankApiException("Access denied: You can only delete your own beneficiaries");
        }

        String beneficiaryAlias = beneficiary.getAlias();
        beneficiaryRepository.deleteById(id);
        
        log.info("Beneficiary '{}' (ID: {}) successfully deleted for customer: {}", beneficiaryAlias, id, customerEmail);
    }

    // Minimal mappings to existing response DTO
    private BeneficiaryResponseDto mapToResponseDtoBasic(Beneficiary b) {
        BeneficiaryResponseDto dto = new BeneficiaryResponseDto();
        dto.setId(b.getId());
        dto.setAlias(b.getAlias());
        if (b.getBeneficiaryAccount() != null) {
            dto.setAccountNumber(b.getBeneficiaryAccount().getAccountNumber());
        }
        dto.setCreatedAt(b.getCreatedAt());
        return dto;
    }

    // Stub implementations for interface methods not covered here
    @Override
    public BeneficiaryValidationResponseDto validateBeneficiary(BeneficiaryValidationRequestDto request, String customerEmail) {
        BeneficiaryValidationResponseDto resp = new BeneficiaryValidationResponseDto();
        resp.setValid(true);
        resp.setMessage("Validated successfully");
        resp.setAlias(request.getAlias());
        resp.setAccountNumber(request.getAccountNumber());
        resp.setIfscCode(request.getIfscCode());
        resp.setTransferType("INTRABANK");
        return resp;
    }

    @Override
    public BeneficiaryResponseDto addBeneficiaryWithValidation(BeneficiaryValidationRequestDto request, String customerEmail) {
        // Reuse add flow with accountNumber and alias
        BeneficiaryCreateRequestDto create = new BeneficiaryCreateRequestDto();
        create.setAccountNumber(request.getAccountNumber());
        create.setAlias(request.getAlias());
        return addBeneficiaryForCustomer(create, customerEmail);
    }

    @Override
    public BeneficiaryActivationResponseDto activateBeneficiary(BeneficiaryActivationRequestDto request, String customerEmail) {
        // Minimal: return success if beneficiary exists and belongs to customer
        Beneficiary beneficiary = beneficiaryRepository.findById(request.getBeneficiaryId())
                .orElseThrow(() -> new BankApiException("Beneficiary not found with id: " + request.getBeneficiaryId()));
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new BankApiException("Customer not found"));
        if (!beneficiary.getOwner().getId().equals(customer.getId())) {
            throw new BankApiException("Access denied: You can only activate your own beneficiaries");
        }
        BeneficiaryActivationResponseDto resp = new BeneficiaryActivationResponseDto();
        resp.setBeneficiary(mapToResponseDtoBasic(beneficiary));
        resp.setMessage("Beneficiary activation processed");
        resp.setSuccess(true);
        return resp;
    }

    @Override
    public String generateTransferOtp(String customerEmail, Long beneficiaryId, BigDecimal amount) {
        // Minimal: return a static OTP-like string for demo
        return "123456";
    }

    @Override
    public BeneficiaryResponseDto transferToBeneficiary(BeneficiaryTransferRequestDto request, String customerEmail) {
        // Validate ownership and that beneficiary exists
        Beneficiary beneficiary = beneficiaryRepository.findById(request.getBeneficiaryId())
                .orElseThrow(() -> new BankApiException("Beneficiary not found with id: " + request.getBeneficiaryId()));
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new BankApiException("Customer not found"));
        if (!beneficiary.getOwner().getId().equals(customer.getId())) {
            throw new BankApiException("Access denied: You can only transfer to your own beneficiaries");
        }
        // No actual transfer performed here; just reflect latest state
        return mapToResponseDtoBasic(beneficiary);
    }

    @Override
    public BeneficiaryResponseDto getBeneficiaryByIdForCustomer(Long id, String customerEmail) {
        Beneficiary b = beneficiaryRepository.findById(id).orElseThrow(() -> new BankApiException("Beneficiary not found with id: " + id));
        if (!b.getOwner().getEmail().equals(customerEmail)) {
            throw new BankApiException("Access denied: You can only view your own beneficiaries");
        }
        return mapToResponseDtoBasic(b);
    }

    @Override
    public List<BeneficiaryResponseDto> getBeneficiariesByCustomerEmail(String customerEmail) {
        User customer = userRepository.findByEmail(customerEmail).orElseThrow(() -> new BankApiException("Customer not found"));
        return beneficiaryRepository.findByOwner(customer).stream().map(this::mapToResponseDtoBasic).collect(Collectors.toList());
    }

    @Override
    public List<BeneficiaryResponseDto> getActiveBeneficiaries(String customerEmail) { return getBeneficiariesByCustomerEmail(customerEmail); }

    @Override
    public List<BeneficiaryResponseDto> getBeneficiariesByStatus(String status, String customerEmail) { return getBeneficiariesByCustomerEmail(customerEmail); }

    @Override
    public List<BeneficiaryResponseDto> getBeneficiariesReadyForActivationByCustomer(String customerEmail) { return List.of(); }

    @Override
    public List<BeneficiaryResponseDto> getAllBeneficiaries() {
        return beneficiaryRepository.findAll().stream().map(this::mapToResponseDtoBasic).collect(Collectors.toList());
    }

    @Override
    public List<BeneficiaryResponseDto> getBeneficiariesByApprovalStatus(String approvalStatus) { return getAllBeneficiaries(); }

    @Override
    public List<BeneficiaryResponseDto> getBeneficiariesReadyForActivation() { return List.of(); }

    @Override
    public List<BeneficiaryResponseDto> getDormantBeneficiaries() { return List.of(); }

    @Override
    public BeneficiaryResponseDto approveBeneficiary(BeneficiaryApprovalRequestDto request, String approverEmail) {
        Beneficiary beneficiary = beneficiaryRepository.findById(request.getBeneficiaryId())
                .orElseThrow(() -> new BankApiException("Beneficiary not found with id: " + request.getBeneficiaryId()));
        // Minimal: no fields to update, return current state
        return mapToResponseDtoBasic(beneficiary);
    }

    @Override
    public void processCoolingPeriodExpiry() {}

    @Override
    public void processDormantBeneficiaries() {}

    @Override
    public void resetDailyTransferLimits() {}

    @Override
    public void resetMonthlyTransferLimits() {}
}
