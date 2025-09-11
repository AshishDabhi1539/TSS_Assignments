package com.tss.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.Branch;
import com.tss.banking.entity.BranchAssignment;
import com.tss.banking.entity.enums.AssignmentType;

public interface BranchAssignmentRepository extends JpaRepository<BranchAssignment, Long> {
    boolean existsByBranchAndAssignmentType(Branch branch, AssignmentType assignmentType);
}