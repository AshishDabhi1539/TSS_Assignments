package com.tss.policy.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class InsurancePolicyResponseDto {
    private String policyNumber;
    private String holderName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double amount;
}