package com.tss.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findByBankId(Long bankId);
    long countByCodeStartingWith(String prefix);
    long countByBankId(Long bankId);
    boolean existsByNameAndBankId(String name, Long bankId);
    boolean existsByCode(String code);
    boolean existsByIfsc(String ifsc);
}