package com.tss.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}