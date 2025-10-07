package com.tss.banking.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {
    
    @NotBlank(message = "Account type is required")
    @Pattern(regexp = "^(SAVINGS|CURRENT|FIXED_DEPOSIT|RECURRING_DEPOSIT)$", 
             message = "Account type must be SAVINGS, CURRENT, FIXED_DEPOSIT, or RECURRING_DEPOSIT")
    private String accountType;
    
    // Initial balance defaults to 2000, optional to override
    @DecimalMin(value = "0.0", message = "Initial balance cannot be negative")
    private BigDecimal initialBalance = new BigDecimal("2000.00");

    private Long customerId; // Optional - can be inferred from authenticated user
    
    @NotNull(message = "Branch ID is required")
    private Long branchId;
    
    @Pattern(regexp = "^(INR|USD|EUR)$", message = "Currency must be INR, USD, or EUR")
    private String currency = "INR";
}