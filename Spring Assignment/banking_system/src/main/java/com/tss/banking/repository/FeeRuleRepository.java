package com.tss.banking.repository;

<<<<<<< HEAD
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.FeeRule;

public interface FeeRuleRepository extends JpaRepository<FeeRule, Long> {
    List<FeeRule> findByEffectiveToIsNullOrEffectiveToAfter(LocalDate date);
    List<FeeRule> findByFeeTypeIgnoreCase(String feeType);
}


=======
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tss.banking.entity.FeeRule;
import com.tss.banking.entity.enums.TransactionType;

@Repository
public interface FeeRuleRepository extends JpaRepository<FeeRule, Long> {
    
    @Query("SELECT f FROM FeeRule f WHERE f.transactionType = :transactionType AND f.isActive = true")
    List<FeeRule> findActiveByTransactionType(@Param("transactionType") TransactionType transactionType);
    
    @Query("SELECT f FROM FeeRule f WHERE f.isActive = true")
    List<FeeRule> findAllActive();
    
    Optional<FeeRule> findByTransactionTypeAndIsActive(TransactionType transactionType, Boolean isActive);
}
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
