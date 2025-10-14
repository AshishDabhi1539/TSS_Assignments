package com.tss.banking.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

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


