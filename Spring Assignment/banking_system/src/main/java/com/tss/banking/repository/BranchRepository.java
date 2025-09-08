package com.tss.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}