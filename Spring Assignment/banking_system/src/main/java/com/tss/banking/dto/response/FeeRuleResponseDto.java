package com.tss.banking.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeRuleResponseDto {
    
    private Long id;
    private String transactionType;
    private String feeType;
    private BigDecimal feeAmount;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
