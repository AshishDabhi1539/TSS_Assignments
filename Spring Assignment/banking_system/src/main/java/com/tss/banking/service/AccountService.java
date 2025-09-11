package com.tss.banking.service;

import java.util.List;

import com.tss.banking.dto.request.AccountRequestDto;
import com.tss.banking.dto.response.AccountResponseDto;

public interface AccountService {
    AccountResponseDto createAccount(AccountRequestDto dto);
    AccountResponseDto createAccountForCustomer(AccountRequestDto dto, String customerEmail);
    AccountResponseDto getAccountById(Long id);
    List<AccountResponseDto> getAccountsByCustomerId(Long customerId);
    List<AccountResponseDto> getAccountsByCustomerEmail(String customerEmail);
}