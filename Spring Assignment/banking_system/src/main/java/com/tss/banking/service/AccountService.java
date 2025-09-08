package com.tss.banking.service;

import com.tss.banking.dto.request.AccountRequestDto;
import com.tss.banking.dto.response.AccountResponseDto;

public interface AccountService {
    AccountResponseDto createAccount(AccountRequestDto dto);
    AccountResponseDto getAccountById(Long id);
}