package com.tss.security.service;

import java.util.List;

import com.tss.security.dto.AccountDto;
import com.tss.security.dto.AccountResponseDto;

public interface AccountService {
    
    AccountResponseDto addAccount(AccountDto accountDto);
    
    List<AccountResponseDto> readAllAccount();
    
    AccountResponseDto readParticularAccount(Long id);
    
    AccountResponseDto disableAccount(Long id);
}
