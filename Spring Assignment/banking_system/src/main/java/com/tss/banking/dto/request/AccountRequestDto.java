package com.tss.banking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {
    private String accountNumber;
    private String accountType; // e.g., SAVINGS, CURRENT
    private double initialBalance;
    private Long customerId;
    private Long branchId;
}