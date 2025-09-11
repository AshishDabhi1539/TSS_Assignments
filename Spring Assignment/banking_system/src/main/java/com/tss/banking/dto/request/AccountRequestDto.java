package com.tss.banking.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {
    
    @NotBlank(message = "Account type is required")
    @Pattern(regexp = "^(SAVINGS|CURRENT|FIXED_DEPOSIT|RECURRING_DEPOSIT)$", 
             message = "Account type must be SAVINGS, CURRENT, FIXED_DEPOSIT, or RECURRING_DEPOSIT")
    private String accountType;
    
    // Initial balance defaults to 2000, optional to override
    @DecimalMin(value = "0.0", message = "Initial balance cannot be negative")
    private double initialBalance = 2000.0;
    
    // Optional for customer self-service, required for admin operations
    private Long customerId;
    
    @NotNull(message = "Branch ID is required")
    private Long branchId;
}