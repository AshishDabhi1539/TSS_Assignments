package com.tss.banking.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestRateConfigRequestDto {
    
    // Optional - null for bank-wide default
    private Long branchId;
    
    @NotBlank(message = "Account type is required")
    @Pattern(regexp = "^(SAVINGS|CURRENT|FIXED_DEPOSIT|RECURRING_DEPOSIT)$", 
             message = "Account type must be SAVINGS, CURRENT, FIXED_DEPOSIT, or RECURRING_DEPOSIT")
    private String accountType;
    
    @NotNull(message = "Interest rate is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Interest rate must be non-negative")
    @DecimalMax(value = "1.0", inclusive = true, message = "Interest rate cannot exceed 100% (1.0)")
    private BigDecimal interestRate;
    
    @NotNull(message = "Minimum balance is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum balance must be non-negative")
    private BigDecimal minBalance;
    
    private Boolean isActive = true;
    
    @NotNull(message = "Effective from date is required")
    private LocalDate effectiveFrom;
    
    private LocalDate effectiveTo;
    
    @Size(max = 32, message = "Compounding type cannot exceed 32 characters")
    @Pattern(regexp = "^(SIMPLE|MONTHLY|DAILY)$", message = "Compounding must be SIMPLE, MONTHLY, or DAILY")
    private String compounding = "MONTHLY";
}
