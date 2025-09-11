package com.tss.banking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tss.banking.entity.FeeRule;

@Repository
public interface FeeRuleRepository extends JpaRepository<FeeRule, Long> {
    
    @Query("SELECT f FROM FeeRule f WHERE f.transactionType = :transactionType AND f.isActive = true")
    List<FeeRule> findActiveByTransactionType(@Param("transactionType") String transactionType);
    
    @Query("SELECT f FROM FeeRule f WHERE f.isActive = true")
    List<FeeRule> findAllActive();
    
    Optional<FeeRule> findByTransactionTypeAndIsActive(String transactionType, Boolean isActive);
}
