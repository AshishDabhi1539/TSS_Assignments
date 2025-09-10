package com.tss.banking.service.impl;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public AccountResponseDto createAccount(AccountRequestDto dto) {
        User user = userRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new BankApiException("User not found with ID: " + dto.getCustomerId()));
        Branch branch = branchRepo.findById(dto.getBranchId())
                .orElseThrow(() -> new BankApiException("Branch not found with ID: " + dto.getBranchId()));

        Account account = new Account();
        account.setCustomer(user);
        account.setBranch(branch);
        account.setBalance(BigDecimal.valueOf(dto.getInitialBalance()));
        account.setAccountNumber(generateAccountNumber());
        try {
            account.setAccountType(AccountType.valueOf(dto.getAccountType().toUpperCase()));
        } catch (Exception ex) {
            throw new BankApiException("Invalid account type: " + dto.getAccountType());
        }
        account.setStatus(AccountStatus.ACTIVE);
        Account savedAccount = accountRepo.save(account);
        return mapper.map(savedAccount, AccountResponseDto.class);
    }

    @Override
    public AccountResponseDto getAccountById(Long id) {
        Account account = accountRepo.findById(id)
                .orElseThrow(() -> new BankApiException("Account not found with ID: " + id));
        return mapper.map(account, AccountResponseDto.class);
    }

    private String generateAccountNumber() {
        // Generate account number: BANK_CODE + BRANCH_CODE + TIMESTAMP + RANDOM
        long timestamp = System.currentTimeMillis();
        int random = (int) (Math.random() * 1000);
        return String.format("ACC%d%03d", timestamp % 100000000L, random);
    }
}