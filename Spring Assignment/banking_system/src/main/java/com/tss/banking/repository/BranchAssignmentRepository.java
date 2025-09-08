package com.tss.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.BranchAssignment;

public interface BranchAssignmentRepository extends JpaRepository<BranchAssignment, Long> {
}