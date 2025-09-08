package com.tss.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {
    private Long id;
    private String accountNumber;
    private String accountType;
    private double balance;
    private String status;
    private Long customerId;
    private Long branchId;
}