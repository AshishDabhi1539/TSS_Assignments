package com.tss.banking.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.tss.banking.entity.enums.CustomerStatus;
import com.tss.banking.entity.enums.RoleType;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "customers", indexes = {
        @Index(name = "idx_customers_email", columnList = "email", unique = true),
        @Index(name = "idx_customers_phone", columnList = "phone", unique = true)
})
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String phone;
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private CustomerStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private RoleType role;

    private Boolean softDeleted = false;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Version
    private Long version;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> accounts;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BranchAssignment> branchAssignments;

    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (status == null) {
            status = CustomerStatus.PENDING;
        }
        if (role == null) {
            role = RoleType.CUSTOMER;
        }
    }
}