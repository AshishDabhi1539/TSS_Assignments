package com.tss.policy.service;

import java.util.Optional;

import com.tss.policy.dto.InsurancePolicyRequestDto;
import com.tss.policy.dto.InsurancePolicyResponseDto;
import com.tss.policy.dto.InsurancePolicyResponsePage;
import com.tss.policy.entity.InsurancePolicy;

public interface InsurancePolicyService {
	InsurancePolicyResponsePage readAllPolicies(int pageSize, int pageNo);

	InsurancePolicyResponsePage searchByHolderName(String holderName, int pageSize, int pageNo);

	InsurancePolicyResponsePage searchByLessThanYears(int years, int pageSize, int pageNo);

	InsurancePolicyResponseDto addNewPolicy(InsurancePolicyRequestDto policy);

	Optional<InsurancePolicy> readPolicyById(Long id);

	InsurancePolicyResponseDto searchByPolicyNumber(String policyNumber);

	boolean deletePolicyByPolicyNumber(String policyNumber);
}