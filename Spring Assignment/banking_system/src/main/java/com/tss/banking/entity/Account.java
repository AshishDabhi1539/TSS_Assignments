package com.tss.banking.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.tss.banking.entity.enums.AccountStatus;
import com.tss.banking.entity.enums.AccountType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "accounts", indexes = {
        @Index(name = "idx_accounts_account_number", columnList = "accountNumber", unique = true),
        @Index(name = "idx_accounts_customer", columnList = "customer_id"),
        @Index(name = "idx_accounts_branch", columnList = "branch_id")
})
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 64)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private AccountType accountType;

    private double balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private AccountStatus status;

    private double interestRate;

    private Double minBalance;

    @Column(length = 3)
    private String currency;

    private LocalDateTime openedAt;

    private LocalDateTime closedAt;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    @PrePersist
    void onCreate() {
        if (openedAt == null) {
            openedAt = LocalDateTime.now();
        }
        if (currency == null) {
            currency = "INR";
        }
        if (status == null) {
            status = AccountStatus.ACTIVE;
        }
    }
}