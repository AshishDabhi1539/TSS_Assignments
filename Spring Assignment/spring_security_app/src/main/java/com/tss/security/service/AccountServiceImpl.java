package com.tss.security.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.security.dto.AccountDto;
import com.tss.security.dto.AccountResponseDto;
import com.tss.security.entity.Account;
import com.tss.security.exception.UserApiException;
import com.tss.security.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountResponseDto addAccount(AccountDto accountDto) {
        // Convert DTO to Entity
        Account account = convertToEntity(accountDto);
        
        // Generate unique account number if not provided
        if (account.getAccountNumber() == null || account.getAccountNumber().isEmpty()) {
            account.setAccountNumber(generateAccountNumber());
        }
        
        // Check if account number already exists
        if (accountRepository.findByAccountNumber(account.getAccountNumber()).isPresent()) {
            throw new UserApiException("Account number already exists: " + account.getAccountNumber());
        }
        
        // Set default values
        if (account.getBalance() == null) {
            account.setBalance(0.0);
        }
        
        if (account.getIsActive() == null) {
            account.setIsActive(true);
        }
        
        Account savedAccount = accountRepository.save(account);
        return convertToResponseDto(savedAccount);
    }

    @Override
    public List<AccountResponseDto> readAllAccount() {
        List<Account> accounts = accountRepository.findByIsActive(true);
        return accounts.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDto readParticularAccount(Long id) {
        Account account = accountRepository.findByIdAndIsActive(id, true)
                .orElseThrow(() -> new UserApiException("Account not found with id: " + id));
        return convertToResponseDto(account);
    }

    @Override
    public AccountResponseDto disableAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new UserApiException("Account not found with id: " + id));
        
        account.setIsActive(false);
        Account savedAccount = accountRepository.save(account);
        return convertToResponseDto(savedAccount);
    }
    
    private String generateAccountNumber() {
        return "ACC" + UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
    }
    
    // Helper methods for DTO conversion
    private Account convertToEntity(AccountDto accountDto) {
        Account account = new Account();
        account.setName(accountDto.getName());
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setBalance(accountDto.getBalance());
        return account;
    }

    private AccountResponseDto convertToResponseDto(Account account) {
        AccountResponseDto responseDto = new AccountResponseDto();
        responseDto.setId(account.getId());
        responseDto.setName(account.getName());
        responseDto.setAccountNumber(account.getAccountNumber());
        responseDto.setBalance(account.getBalance());
        responseDto.setIsActive(account.getIsActive());
        return responseDto;
    }
}
