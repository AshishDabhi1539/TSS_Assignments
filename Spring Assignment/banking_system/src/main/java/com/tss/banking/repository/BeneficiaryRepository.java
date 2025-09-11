package com.tss.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tss.banking.entity.Beneficiary;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    
    @Query("SELECT b FROM Beneficiary b WHERE b.owner.id = :ownerId")
    List<Beneficiary> findByOwnerId(@Param("ownerId") Long ownerId);
    
    @Query("SELECT b FROM Beneficiary b WHERE b.owner.id = :ownerId AND b.beneficiaryAccount.id = :accountId")
    List<Beneficiary> findByOwnerIdAndBeneficiaryAccountId(@Param("ownerId") Long ownerId, @Param("accountId") Long accountId);
}
