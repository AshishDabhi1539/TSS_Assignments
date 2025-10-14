package com.tss.banking.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class InterestRateConfigRequestDto {

    private Long branchId; // optional; null for bank-wide

    @NotBlank
    private String accountType; // enum name

    @NotNull
    @PositiveOrZero
    private BigDecimal annualRatePercent;

    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private String compounding; // SIMPLE, MONTHLY, DAILY

    public Long getBranchId() { return branchId; }
    public void setBranchId(Long branchId) { this.branchId = branchId; }
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public BigDecimal getAnnualRatePercent() { return annualRatePercent; }
    public void setAnnualRatePercent(BigDecimal annualRatePercent) { this.annualRatePercent = annualRatePercent; }
    public LocalDate getEffectiveFrom() { return effectiveFrom; }
    public void setEffectiveFrom(LocalDate effectiveFrom) { this.effectiveFrom = effectiveFrom; }
    public LocalDate getEffectiveTo() { return effectiveTo; }
    public void setEffectiveTo(LocalDate effectiveTo) { this.effectiveTo = effectiveTo; }
    public String getCompounding() { return compounding; }
    public void setCompounding(String compounding) { this.compounding = compounding; }
}


=======
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
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
