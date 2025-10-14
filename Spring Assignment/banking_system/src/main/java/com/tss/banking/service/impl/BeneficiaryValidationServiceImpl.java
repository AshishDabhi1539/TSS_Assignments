package com.tss.banking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.banking.dto.request.BeneficiaryValidationRequestDto;
import com.tss.banking.dto.response.BeneficiaryValidationResponseDto;
import com.tss.banking.entity.Account;
import com.tss.banking.entity.User;
import com.tss.banking.repository.AccountRepository;
import com.tss.banking.repository.BranchRepository;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.BeneficiaryValidationService;

@Service
public class BeneficiaryValidationServiceImpl implements BeneficiaryValidationService {

    private static final Logger log = LoggerFactory.getLogger(BeneficiaryValidationServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BranchRepository branchRepository;

    // Note: BeneficiaryRepository is not present in this codebase snapshot.
    // Keep beneficiary existence checks limited to account-level checks.

    @Autowired
    private UserRepository userRepository;

    // Regex patterns for validation
    private static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile("^[A-Z]{3}\\d{9}$"); // ACC123456789
    private static final Pattern IFSC_CODE_PATTERN = Pattern.compile("^[A-Z]{3}\\d{7}[A-Z]{2}$"); // FCB0010001HO
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]{2,50}$");

    @Override
    public BeneficiaryValidationResponseDto validateBeneficiary(BeneficiaryValidationRequestDto request, Long customerId) {
        List<String> errors = new ArrayList<>();
        List<String> warnings = new ArrayList<>();

        log.info("Starting beneficiary validation for customer: {} account: {}", customerId, request.getAccountNumber());

        // 1. Validate account number format
        if (!isValidAccountNumber(request.getAccountNumber())) {
            errors.add("Invalid account number format. Expected format: ACC123456789");
        }

        // 2. Validate IFSC code format
        if (!isValidIfscCode(request.getIfscCode())) {
            errors.add("Invalid IFSC code format. Expected format: FCB0010001HO");
        }

        // 3. Validate account holder name
        if (!isValidAccountHolderName(request.getAccountHolderName())) {
            errors.add("Invalid account holder name. Only alphabets and spaces allowed (2-50 characters)");
        }

        // 4. Check if account exists
        if (!accountExists(request.getAccountNumber())) {
            errors.add("Account number does not exist in our system");
        } else {
            // 5. Check if customer is trying to add their own account
            if (isSelfAccount(customerId, request.getAccountNumber())) {
                errors.add("Cannot add your own account as beneficiary");
            }

            // 6. Verify IFSC code matches account's branch
            Account account = accountRepository.findByAccountNumber(request.getAccountNumber()).orElse(null);
            if (account != null && !account.getBranch().getIfsc().equals(request.getIfscCode())) {
                errors.add("IFSC code does not match the account's branch. Expected: " + account.getBranch().getIfsc());
            }

            // 7. Verify account holder name
            if (account != null) {
                String actualName = account.getCustomer().getFirstName() + " " + account.getCustomer().getLastName();
                if (!actualName.trim().equalsIgnoreCase(request.getAccountHolderName().trim())) {
                    errors.add("Account holder name does not match our records. Expected: " + actualName);
                }
            }

            // 8. Optionally, prevent duplicates if a repository exists (skipped)

            // 9. Check account status
            if (account != null && !"ACTIVE".equals(account.getStatus().toString())) {
                warnings.add("Target account is not active. Status: " + account.getStatus());
            }
        }

        // 10. Check if IFSC code exists (if account doesn't exist)
        if (!accountExists(request.getAccountNumber()) && !ifscCodeExists(request.getIfscCode())) {
            errors.add("IFSC code does not exist in our system");
        }

        // 11. Validate alias if provided
        if (request.getAlias() != null && request.getAlias().trim().length() < 2) {
            errors.add("Alias must be at least 2 characters long");
        }

        // 12. Additional business validations
        if (request.getAlias() != null && request.getAlias().trim().length() > 50) {
            errors.add("Alias cannot exceed 50 characters");
        }

        boolean isValid = errors.isEmpty();
        
        BeneficiaryValidationResponseDto response = new BeneficiaryValidationResponseDto();
        response.setValid(isValid);
        response.setErrors(errors);
        response.setWarnings(warnings);
        response.setAccountNumber(request.getAccountNumber());
        response.setIfscCode(request.getIfscCode());
        response.setAccountHolderName(request.getAccountHolderName());
        response.setAlias(request.getAlias());

        if (isValid) {
            response.setMessage("Beneficiary validation successful");
            response.setTransferType(determineTransferType(request.getIfscCode()));
        } else {
            response.setMessage("Beneficiary validation failed");
        }

        log.info("Beneficiary validation completed for customer: {} - Valid: {} Errors: {} Warnings: {}", 
                customerId, isValid, errors.size(), warnings.size());

        return response;
    }

    @Override
    public boolean isValidAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            return false;
        }
        return ACCOUNT_NUMBER_PATTERN.matcher(accountNumber.trim()).matches();
    }

    @Override
    public boolean isValidIfscCode(String ifscCode) {
        if (ifscCode == null || ifscCode.trim().isEmpty()) {
            return false;
        }
        return IFSC_CODE_PATTERN.matcher(ifscCode.trim()).matches();
    }

    @Override
    public boolean accountExists(String accountNumber) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            return false;
        }
        return accountRepository.existsByAccountNumber(accountNumber.trim());
    }

    @Override
    public boolean ifscCodeExists(String ifscCode) {
        if (ifscCode == null || ifscCode.trim().isEmpty()) {
            return false;
        }
        return branchRepository.existsByIfsc(ifscCode.trim());
    }

    @Override
    public boolean isValidAccountHolderName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return NAME_PATTERN.matcher(name.trim()).matches();
    }

    @Override
    public boolean beneficiaryExists(Long customerId, String accountNumber) { return false; }

    @Override
    public boolean isSelfAccount(Long customerId, String accountNumber) {
        if (customerId == null || accountNumber == null || accountNumber.trim().isEmpty()) {
            return false;
        }
        
        User customer = userRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return false;
        }
        
        Account account = accountRepository.findByAccountNumber(accountNumber.trim()).orElse(null);
        if (account == null) {
            return false;
        }
        
        return account.getCustomer().getId().equals(customerId);
    }

    private String determineTransferType(String ifscCode) {
        if (ifscCode != null && ifscCode.startsWith("FCB")) {
            return "INTRABANK";
        }
        return "INTERBANK";
    }
}
