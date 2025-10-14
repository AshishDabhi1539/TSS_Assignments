package com.tss.banking.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FeeRuleResponseDto {
    private Long id;
    private String feeType;
    private BigDecimal fixedAmount;
    private BigDecimal percentAmount;
    private BigDecimal threshold;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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


