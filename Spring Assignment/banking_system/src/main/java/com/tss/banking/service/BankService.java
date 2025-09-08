package com.tss.banking.service;

import com.tss.banking.dto.request.BankRequestDto;
import com.tss.banking.dto.response.BankResponseDto;

public interface BankService {
    BankResponseDto createBank(BankRequestDto dto);
    BankResponseDto getBankById(Long id);
}