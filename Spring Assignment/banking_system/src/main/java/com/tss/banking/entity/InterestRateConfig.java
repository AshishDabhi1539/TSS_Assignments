package com.tss.banking.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.tss.banking.entity.enums.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "interest_rate_configs", indexes = {
        @Index(name = "idx_irc_branch_type", columnList = "branch_id,accountType,effectiveFrom", unique = true)
})
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class InterestRateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch; // null for bank-wide default

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private AccountType accountType;

    @Column(precision = 5, scale = 4)
    private BigDecimal interestRate;

    @Column(precision = 15, scale = 2)
    private BigDecimal minBalance;

    @Column(nullable = false)
    private Boolean isActive = true;

    private LocalDate effectiveFrom;

    private LocalDate effectiveTo;

    @Column(length = 32)
    private String compounding; // SIMPLE, MONTHLY, DAILY
}


