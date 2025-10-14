package com.tss.banking.repository;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findByBankId(Long bankId);
<<<<<<< HEAD
    boolean existsByIfsc(String ifsc);
    Optional<Branch> findByIfsc(String ifsc);
=======
    long countByCodeStartingWith(String prefix);
    long countByBankId(Long bankId);
    boolean existsByNameAndBankId(String name, Long bankId);
    boolean existsByCode(String code);
    boolean existsByIfsc(String ifsc);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
}