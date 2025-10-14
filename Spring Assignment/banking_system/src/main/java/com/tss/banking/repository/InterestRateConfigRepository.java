package com.tss.banking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.InterestRateConfig;

public interface InterestRateConfigRepository extends JpaRepository<InterestRateConfig, Long> {
    List<InterestRateConfig> findByEffectiveToIsNullOrEffectiveToAfter(LocalDate date);
}


