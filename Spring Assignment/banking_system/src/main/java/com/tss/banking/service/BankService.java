package com.tss.banking.service;

import java.util.List;

import com.tss.banking.dto.request.BankRequestDto;
import com.tss.banking.dto.response.BankResponseDto;

public interface BankService {
    BankResponseDto createBank(BankRequestDto dto);
    BankResponseDto getBankById(Long id);
    List<BankResponseDto> getAllBanks();
}