package com.tss.banking.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestRateConfigRequestDto {
    
    @NotBlank(message = "Account type is required")
    @Size(max = 50, message = "Account type must not exceed 50 characters")
    private String accountType;
    
    @NotNull(message = "Interest rate is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Interest rate must be non-negative")
    @DecimalMax(value = "100.0", inclusive = true, message = "Interest rate cannot exceed 100%")
    private BigDecimal interestRate;
    
    @NotNull(message = "Minimum balance is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum balance must be non-negative")
    private BigDecimal minBalance;
    
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;
    
    @NotNull(message = "Active status is required")
    private Boolean isActive;
}
