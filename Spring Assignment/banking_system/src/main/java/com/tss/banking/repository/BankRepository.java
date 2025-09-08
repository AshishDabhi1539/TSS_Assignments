package com.tss.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {
}