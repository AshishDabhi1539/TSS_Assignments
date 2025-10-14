package com.tss.banking.repository;

<<<<<<< HEAD
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.InterestRateConfig;

public interface InterestRateConfigRepository extends JpaRepository<InterestRateConfig, Long> {
    List<InterestRateConfig> findByEffectiveToIsNullOrEffectiveToAfter(LocalDate date);
}


=======
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tss.banking.entity.InterestRateConfig;

@Repository
public interface InterestRateConfigRepository extends JpaRepository<InterestRateConfig, Long> {
    
    @Query("SELECT i FROM InterestRateConfig i WHERE i.accountType = :accountType AND i.isActive = true")
    List<InterestRateConfig> findActiveByAccountType(@Param("accountType") String accountType);
    
    @Query("SELECT i FROM InterestRateConfig i WHERE i.isActive = true")
    List<InterestRateConfig> findAllActive();
    
    Optional<InterestRateConfig> findByAccountTypeAndIsActive(String accountType, Boolean isActive);
}
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
