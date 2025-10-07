package com.tss.banking.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "fee_rules", indexes = {
        @Index(name = "idx_fee_transaction_type", columnList = "transactionType,isActive")
})
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class FeeRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String transactionType; // TRANSFER, DEBIT, CREDIT, etc.

    @Column(nullable = false, length = 16)
    private String feeType; // FLAT, PERCENTAGE

    @Column(precision = 15, scale = 2)
    private BigDecimal feeAmount;

    @Column(precision = 15, scale = 2)
    private BigDecimal minAmount;

    @Column(precision = 15, scale = 2)
    private BigDecimal maxAmount;

    @Column(nullable = false)
    private Boolean isActive = true;

    private LocalDate effectiveFrom;

    private LocalDate effectiveTo;
}


