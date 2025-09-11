package com.tss.banking.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "banks", indexes = {
        @Index(name = "idx_banks_code", columnList = "code", unique = true)
})
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 128)
    private String name;
    
    @Column(length = 512)
    private String address;
    
    @Column(unique = true, nullable = false, length = 32)
    private String code;
    
    @Column(length = 3, nullable = false)
    private String currency;
    
    @Column(length = 64)
    private String country;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Branch> branches;

    @PrePersist
    void onCreate() {
        if (currency == null) {
            currency = "INR";
        }
    }
}