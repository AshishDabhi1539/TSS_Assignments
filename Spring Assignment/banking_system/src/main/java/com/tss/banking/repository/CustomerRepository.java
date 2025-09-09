package com.tss.banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByPhone(String phone);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}