package com.tss.banking.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tss.banking.dto.request.AccountRequestDto;
import com.tss.banking.dto.response.AccountResponseDto;
import com.tss.banking.entity.Account;
import com.tss.banking.entity.Branch;
import com.tss.banking.entity.User;
import com.tss.banking.entity.enums.AccountStatus;
import com.tss.banking.entity.enums.AccountType;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.AccountRepository;
import com.tss.banking.repository.BranchRepository;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BranchRepository branchRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public AccountResponseDto createAccount(AccountRequestDto dto) {
        try {
            User user = userRepo.findById(dto.getCustomerId())
                    .orElseThrow(() -> new BankApiException("User not found with ID: " + dto.getCustomerId()));
            Branch branch = branchRepo.findById(dto.getBranchId())
                    .orElseThrow(() -> new BankApiException("Branch not found with ID: " + dto.getBranchId()));

            // Check if user already has this account type in this branch
            if (accountRepo.existsByCustomerAndBranchAndAccountType(user, branch, AccountType.valueOf(dto.getAccountType().toUpperCase()))) {
                throw new BankApiException("User already has a " + dto.getAccountType() + " account in this branch");
            }

            // Handle null initial balance
            BigDecimal initialBalance = dto.getInitialBalance();
            if (initialBalance == null) {
                initialBalance = new BigDecimal("2000.00");
            }

            Account account = new Account();
            account.setCustomer(user);
            account.setBranch(branch);
            account.setBalance(initialBalance);
            account.setAccountNumber(generateAccountNumber());
            try {
                account.setAccountType(AccountType.valueOf(dto.getAccountType().toUpperCase()));
            } catch (Exception ex) {
                throw new BankApiException("Invalid account type: " + dto.getAccountType());
            }
            account.setStatus(AccountStatus.ACTIVE);
            
            // Note: Minimum balance validation will be handled by @PrePersist in Account entity
            
            Account savedAccount = accountRepo.save(account);
            return mapToAccountResponseDto(savedAccount);
        } catch (Exception e) {
            throw new BankApiException("Failed to create account: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public AccountResponseDto createAccountForCustomer(AccountRequestDto dto, String customerEmail) {
        try {
            User user = userRepo.findByEmail(customerEmail)
                    .orElseThrow(() -> new BankApiException("User not found with email: " + customerEmail));

            Branch branch = branchRepo.findById(dto.getBranchId())
                    .orElseThrow(() -> new BankApiException("Branch not found with ID: " + dto.getBranchId()));

            // Check if user already has this account type in this branch
            if (accountRepo.existsByCustomerAndBranchAndAccountType(user, branch, AccountType.valueOf(dto.getAccountType().toUpperCase()))) {
                throw new BankApiException("You already have a " + dto.getAccountType() + " account in this branch");
            }

            // Handle null initial balance
            BigDecimal initialBalance = dto.getInitialBalance();
            if (initialBalance == null) {
                initialBalance = new BigDecimal("2000.00");
            }

            Account account = new Account();
            account.setCustomer(user);
            account.setBranch(branch);
            account.setBalance(initialBalance);
            account.setAccountNumber(generateAccountNumber());
            try {
                account.setAccountType(AccountType.valueOf(dto.getAccountType().toUpperCase()));
            } catch (Exception ex) {
                throw new BankApiException("Invalid account type: " + dto.getAccountType());
            }
            account.setStatus(AccountStatus.ACTIVE);
            
            // Note: Minimum balance validation will be handled by @PrePersist in Account entity
            
            Account savedAccount = accountRepo.save(account);
            return mapToAccountResponseDto(savedAccount);
        } catch (Exception e) {
            throw new BankApiException("Failed to create account: " + e.getMessage());
        }
    }

    @Override
    public AccountResponseDto getAccountById(Long id) {
        Account account = accountRepo.findById(id)
                .orElseThrow(() -> new BankApiException("Account not found with ID: " + id));
        return mapper.map(account, AccountResponseDto.class);
    }

    @Override
    public List<AccountResponseDto> getAccountsByCustomerId(Long customerId) {
        User user = userRepo.findById(customerId)
                .orElseThrow(() -> new BankApiException("User not found with ID: " + customerId));
        return accountRepo.findByCustomer(user).stream()
                .map(account -> mapper.map(account, AccountResponseDto.class))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<AccountResponseDto> getAccountsByCustomerEmail(String customerEmail) {
        User user = userRepo.findByEmail(customerEmail)
                .orElseThrow(() -> new BankApiException("User not found with email: " + customerEmail));
        return accountRepo.findByCustomer(user).stream()
                .map(account -> mapper.map(account, AccountResponseDto.class))
                .collect(java.util.stream.Collectors.toList());
    }

    private String generateAccountNumber() {
        // Generate account number: BANK_CODE + BRANCH_CODE + SEQUENCE
        long count = accountRepo.count();
        return String.format("ACC%010d", count + 1);
    }
    
    private AccountResponseDto mapToAccountResponseDto(Account account) {
        AccountResponseDto dto = mapper.map(account, AccountResponseDto.class);
        
        // Populate customer information
        if (account.getCustomer() != null) {
            dto.setCustomerId(account.getCustomer().getId());
            dto.setCustomerName(account.getCustomer().getFirstName() + " " + account.getCustomer().getLastName());
        }
        
        // Populate branch information
        if (account.getBranch() != null) {
            dto.setBranchId(account.getBranch().getId());
            dto.setBranchName(account.getBranch().getName());
            dto.setBranchCode(account.getBranch().getCode());
        }
        
        return dto;
    }
}