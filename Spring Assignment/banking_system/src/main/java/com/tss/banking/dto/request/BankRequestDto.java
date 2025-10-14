package com.tss.banking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BankRequestDto {
    private String name;
    private String address;
    private String code; // optional, auto-generate if null
    private String currency; // default INR
    private String country; // optional
    // Optional overrides for default branch on bank creation
    private String defaultBranchName;
    private String defaultBranchCode;
    private String defaultBranchIfsc;
}