package com.tss.policy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.policy.entity.InsurancePolicy;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {
    List<InsurancePolicy> findByHolderName(String holderName);
    InsurancePolicy findByPolicyNumber(String policyNumber);
}