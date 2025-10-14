package com.tss.banking.dto.response;

import java.math.BigDecimal;
<<<<<<< HEAD
import java.time.LocalDate;

public class InterestRateConfigResponseDto {
    private Long id;
    private Long branchId;
    private String accountType;
    private BigDecimal annualRatePercent;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private String compounding;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestRateConfigResponseDto {
    
    private Long id;
    private String accountType;
    private BigDecimal interestRate;
    private BigDecimal minBalance;
    private String description;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
