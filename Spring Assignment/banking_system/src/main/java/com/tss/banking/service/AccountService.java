package com.tss.banking.service;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
import com.tss.banking.dto.request.AccountRequestDto;
import com.tss.banking.dto.response.AccountResponseDto;

public interface AccountService {
    AccountResponseDto createAccount(AccountRequestDto dto);
<<<<<<< HEAD
    AccountResponseDto getAccountById(Long id);
=======
    AccountResponseDto createAccountForCustomer(AccountRequestDto dto, String customerEmail);
    AccountResponseDto getAccountById(Long id);
    List<AccountResponseDto> getAccountsByCustomerId(Long customerId);
    List<AccountResponseDto> getAccountsByCustomerEmail(String customerEmail);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
}