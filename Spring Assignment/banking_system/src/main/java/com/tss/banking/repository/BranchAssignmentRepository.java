package com.tss.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import com.tss.banking.entity.BranchAssignment;

public interface BranchAssignmentRepository extends JpaRepository<BranchAssignment, Long> {
=======
import com.tss.banking.entity.Branch;
import com.tss.banking.entity.BranchAssignment;
import com.tss.banking.entity.User;
import com.tss.banking.entity.enums.AssignmentType;

public interface BranchAssignmentRepository extends JpaRepository<BranchAssignment, Long> {
    boolean existsByBranchAndAssignmentType(Branch branch, AssignmentType assignmentType);
    boolean existsByCustomerAndBranch(User customer, Branch branch);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
}