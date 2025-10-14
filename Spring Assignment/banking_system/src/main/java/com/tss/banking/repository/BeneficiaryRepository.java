package com.tss.banking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.Beneficiary;
import com.tss.banking.entity.User;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    List<Beneficiary> findByOwner(User owner);
    Optional<Beneficiary> findByOwnerIdAndId(Long ownerId, Long id);
    boolean existsByOwnerIdAndBeneficiaryAccountId(Long ownerId, Long beneficiaryAccountId);
}


