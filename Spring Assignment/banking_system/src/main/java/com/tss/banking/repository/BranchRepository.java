package com.tss.banking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findByBankId(Long bankId);
    boolean existsByIfsc(String ifsc);
    Optional<Branch> findByIfsc(String ifsc);
}