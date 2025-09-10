package com.tss.banking.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "fee_rules", indexes = {
        @Index(name = "idx_fee_type", columnList = "feeType,effectiveFrom")
})
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class FeeRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64)
    private String feeType; // e.g., MIN_BALANCE_FEE, INTER_BRANCH_TRANSFER_FEE

    @Column(precision = 15, scale = 2)
    private BigDecimal fixedAmount;

    @Column(precision = 5, scale = 4)
    private BigDecimal percentAmount;

    @Column(precision = 15, scale = 2)
    private BigDecimal threshold;

    private LocalDate effectiveFrom;

    private LocalDate effectiveTo;
}


