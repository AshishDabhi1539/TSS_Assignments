package com.tss.banking.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.banking.dto.request.AccountRequestDto;
import com.tss.banking.dto.response.AccountResponseDto;
import com.tss.banking.entity.Account;
import com.tss.banking.entity.Branch;
import com.tss.banking.entity.Customer;
import com.tss.banking.entity.enums.AccountStatus;
import com.tss.banking.entity.enums.AccountType;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.AccountRepository;
import com.tss.banking.repository.BranchRepository;
import com.tss.banking.repository.CustomerRepository;
import com.tss.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private BranchRepository branchRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public AccountResponseDto createAccount(AccountRequestDto dto) {
        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new BankApiException("Customer not found with ID: " + dto.getCustomerId()));
        Branch branch = branchRepo.findById(dto.getBranchId())
                .orElseThrow(() -> new BankApiException("Branch not found with ID: " + dto.getBranchId()));

        Account account = mapper.map(dto, Account.class);
        account.setCustomer(customer);
        account.setBranch(branch);
        account.setBalance(dto.getInitialBalance());
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
}