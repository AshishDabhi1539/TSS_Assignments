package com.tss.banking.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class FeeRuleRequestDto {

    @NotBlank
    private String feeType;

    @PositiveOrZero
    private BigDecimal fixedAmount;

    @PositiveOrZero
    private BigDecimal percentAmount;

    @PositiveOrZero
    private BigDecimal threshold;

    private LocalDate effectiveFrom;

    private LocalDate effectiveTo;

    public String getFeeType() { return feeType; }
    public void setFeeType(String feeType) { this.feeType = feeType; }
    public BigDecimal getFixedAmount() { return fixedAmount; }
    public void setFixedAmount(BigDecimal fixedAmount) { this.fixedAmount = fixedAmount; }
    public BigDecimal getPercentAmount() { return percentAmount; }
    public void setPercentAmount(BigDecimal percentAmount) { this.percentAmount = percentAmount; }
    public BigDecimal getThreshold() { return threshold; }
    public void setThreshold(BigDecimal threshold) { this.threshold = threshold; }
    public LocalDate getEffectiveFrom() { return effectiveFrom; }
    public void setEffectiveFrom(LocalDate effectiveFrom) { this.effectiveFrom = effectiveFrom; }
    public LocalDate getEffectiveTo() { return effectiveTo; }
    public void setEffectiveTo(LocalDate effectiveTo) { this.effectiveTo = effectiveTo; }
}


=======
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
public class FeeRuleRequestDto {
    
    @NotBlank(message = "Transaction type is required")
    @Size(max = 32, message = "Transaction type must not exceed 32 characters")
    @Pattern(regexp = "^(TRANSFER|DEBIT|CREDIT|DEPOSIT|WITHDRAWAL|ATM_WITHDRAWAL|ONLINE_TRANSFER)$", 
             message = "Invalid transaction type")
    private String transactionType;
    
    @NotBlank(message = "Fee type is required")
    @Size(max = 16, message = "Fee type must not exceed 16 characters")
    @Pattern(regexp = "^(FLAT|PERCENTAGE)$", message = "Fee type must be FLAT or PERCENTAGE")
    private String feeType;
    
    @NotNull(message = "Fee amount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Fee amount must be non-negative")
    private BigDecimal feeAmount;
    
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum amount must be non-negative")
    private BigDecimal minAmount;
    
    @DecimalMin(value = "0.0", inclusive = true, message = "Maximum amount must be non-negative")
    private BigDecimal maxAmount;
    
    private Boolean isActive = true;
    
    private LocalDate effectiveFrom;
    
    private LocalDate effectiveTo;
}
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
