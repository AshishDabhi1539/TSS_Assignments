package com.tss.banking.repository;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.Beneficiary;
import com.tss.banking.entity.User;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    List<Beneficiary> findByOwner(User owner);
    Optional<Beneficiary> findByOwnerIdAndId(Long ownerId, Long id);
    boolean existsByOwnerIdAndBeneficiaryAccountId(Long ownerId, Long beneficiaryAccountId);
}


=======

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
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
