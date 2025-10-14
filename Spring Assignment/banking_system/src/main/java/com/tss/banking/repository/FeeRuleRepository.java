package com.tss.banking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.FeeRule;

public interface FeeRuleRepository extends JpaRepository<FeeRule, Long> {
    List<FeeRule> findByEffectiveToIsNullOrEffectiveToAfter(LocalDate date);
    List<FeeRule> findByFeeTypeIgnoreCase(String feeType);
}


