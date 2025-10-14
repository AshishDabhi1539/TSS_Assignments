package com.tss.banking.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

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


