package com.tss.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountResponseDto {

    private Long id;
    private String name;
    private String accountNumber;
    private Double balance;
    private Boolean isActive;
}
