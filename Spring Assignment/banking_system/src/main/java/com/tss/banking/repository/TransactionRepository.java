package com.tss.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}