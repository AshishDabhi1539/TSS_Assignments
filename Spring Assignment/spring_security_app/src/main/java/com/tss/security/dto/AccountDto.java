package com.tss.security.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountDto {

    @NotBlank(message = "Account name is required")
    private String name;

    private String accountNumber; // Optional - will be auto-generated if not provided

    @NotNull(message = "Balance is required")
    @DecimalMin(value = "0.0", message = "Balance must be non-negative")
    private Double balance;
}
