package com.tss.banking.service.impl;

<<<<<<< HEAD
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
=======
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
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
import com.tss.banking.entity.User;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.AccountRepository;
import com.tss.banking.repository.BeneficiaryRepository;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.BeneficiaryService;
<<<<<<< HEAD
// import com.tss.banking.service.BeneficiaryValidationService;
// import com.tss.banking.service.EmailService;
// import com.tss.banking.service.OtpService;
// import com.tss.banking.service.TransactionService;
=======
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

<<<<<<< HEAD
    private static final Logger log = LoggerFactory.getLogger(BeneficiaryServiceImpl.class);

=======
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

<<<<<<< HEAD
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
=======
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

>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setOwner(customer);
        beneficiary.setBeneficiaryAccount(beneficiaryAccount);
        beneficiary.setAlias(dto.getAlias());
<<<<<<< HEAD
        // Beneficiary entity in this project only supports alias + createdAt
        beneficiary.setCreatedAt(LocalDateTime.now());

        Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);

        log.info("Beneficiary added for customer: {} with account: {}, ID: {}", customerEmail, beneficiaryAccount.getAccountNumber(), savedBeneficiary.getId());

        return mapToResponseDtoBasic(savedBeneficiary);
    }

    @Override
    @Transactional
    public BeneficiaryResponseDto updateBeneficiaryForCustomer(Long id, BeneficiaryUpdateRequestDto dto, String customerEmail) {
=======
        beneficiary.setVerificationStatus(VerificationStatus.PENDING);

        Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);
        return mapToResponseDto(savedBeneficiary);
    }

    @Override
    public BeneficiaryResponseDto getBeneficiaryByIdForCustomer(Long id, String customerEmail) {
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
        Beneficiary beneficiary = beneficiaryRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Beneficiary not found with id: " + id));
        
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new BankApiException("Customer not found"));
        
        if (!beneficiary.getOwner().getId().equals(customer.getId())) {
<<<<<<< HEAD
            throw new BankApiException("Access denied: You can only update your own beneficiaries");
        }

        // Update only the fields that are provided in BeneficiaryUpdateRequestDto
=======
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

>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
        if (dto.getAlias() != null) {
            beneficiary.setAlias(dto.getAlias());
        }

<<<<<<< HEAD
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
=======
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
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
}
