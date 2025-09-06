package com.tss.policy.service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tss.policy.dto.InsurancePolicyRequestDto;
import com.tss.policy.dto.InsurancePolicyResponseDto;
import com.tss.policy.dto.InsurancePolicyResponsePage;
import com.tss.policy.entity.InsurancePolicy;
import com.tss.policy.exception.PolicyApiException;
import com.tss.policy.repository.InsurancePolicyRepository;

@Service
public class InsurancePolicyServiceImpl implements InsurancePolicyService {

	@Autowired
	private InsurancePolicyRepository policyRepo;

	private final Random random = new Random();

	@Override
	public InsurancePolicyResponsePage readAllPolicies(int pageSize, int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<InsurancePolicy> policyPage = policyRepo.findAll(pageable);

		List<InsurancePolicyResponseDto> responses = policyPage.getContent().stream()
				.map(this::policyToPolicyResponseDto).collect(Collectors.toList());

		InsurancePolicyResponsePage policyResponsePage = new InsurancePolicyResponsePage();
		policyResponsePage.setContents(responses);
		policyResponsePage.setTotalElements((int) policyPage.getTotalElements());
		policyResponsePage.setSize(policyPage.getNumberOfElements());
		policyResponsePage.setTotalPages(policyPage.getTotalPages());
		policyResponsePage.setLastPage(policyPage.isLast());

		System.out.println(policyPage);

		return policyResponsePage;
	}

	@Override
	public InsurancePolicyResponsePage searchByHolderName(String holderName, int pageSize, int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		// Fetch all policies and filter by holderName, then apply pagination manually
		Page<InsurancePolicy> allPolicies = policyRepo.findAll(pageable);
		List<InsurancePolicy> filteredPolicies = allPolicies.getContent().stream()
				.filter(p -> p.getHolderName().equalsIgnoreCase(holderName)).collect(Collectors.toList());

		// Manual pagination
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), filteredPolicies.size());
		List<InsurancePolicy> pagedPolicies = filteredPolicies.subList(start, end);

		List<InsurancePolicyResponseDto> responses = pagedPolicies.stream().map(this::policyToPolicyResponseDto)
				.collect(Collectors.toList());

		InsurancePolicyResponsePage policyResponsePage = new InsurancePolicyResponsePage();
		policyResponsePage.setContents(responses);
		policyResponsePage.setTotalElements(filteredPolicies.size());
		policyResponsePage.setSize(pagedPolicies.size());
		policyResponsePage.setTotalPages((int) Math.ceil((double) filteredPolicies.size() / pageable.getPageSize()));
		policyResponsePage.setLastPage(pageNo >= policyResponsePage.getTotalPages() - 1);

		return policyResponsePage;
	}

	@Override
	public InsurancePolicyResponsePage searchByLessThanYears(int years, int pageSize, int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		// Fetch all policies and filter by duration, then apply pagination manually
		Page<InsurancePolicy> allPolicies = policyRepo.findAll(pageable);
		List<InsurancePolicy> filteredPolicies = allPolicies.getContent().stream()
				.filter(p -> p.getStartDate() != null && p.getEndDate() != null
						&& ChronoUnit.YEARS.between(p.getStartDate(), p.getEndDate()) < years)
				.collect(Collectors.toList());

		// Manual pagination
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), filteredPolicies.size());
		List<InsurancePolicy> pagedPolicies = filteredPolicies.subList(start, end);

		List<InsurancePolicyResponseDto> responses = pagedPolicies.stream().map(this::policyToPolicyResponseDto)
				.collect(Collectors.toList());

		InsurancePolicyResponsePage policyResponsePage = new InsurancePolicyResponsePage();
		policyResponsePage.setContents(responses);
		policyResponsePage.setTotalElements(filteredPolicies.size());
		policyResponsePage.setSize(pagedPolicies.size());
		policyResponsePage.setTotalPages((int) Math.ceil((double) filteredPolicies.size() / pageable.getPageSize()));
		policyResponsePage.setLastPage(pageNo >= policyResponsePage.getTotalPages() - 1);

		return policyResponsePage;
	}

	@Override
	public InsurancePolicyResponseDto addNewPolicy(InsurancePolicyRequestDto policyDto) {
		List<InsurancePolicy> existingPolicies = policyRepo.findByHolderName(policyDto.getHolderName());
		boolean duplicate = existingPolicies.stream().anyMatch(p -> p.getStartDate().equals(policyDto.getStartDate())
				&& p.getEndDate().equals(policyDto.getEndDate()));

		if (duplicate) {
			throw new PolicyApiException(
					"Policy already exists for holder " + policyDto.getHolderName() + " in this period.");
		}

		InsurancePolicy policy = policyRequestDtoToPolicy(policyDto);
		InsurancePolicy dbPolicy = policyRepo.save(policy);
		return policyToPolicyResponseDto(dbPolicy);
	}

	@Override
	public Optional<InsurancePolicy> readPolicyById(Long id) {
		return policyRepo.findById(id);
	}

	@Override
	public InsurancePolicyResponseDto searchByPolicyNumber(String policyNumber) {
		InsurancePolicy policy = policyRepo.findByPolicyNumber(policyNumber);
		return policy != null ? policyToPolicyResponseDto(policy) : null;
	}

	@Override
	public boolean deletePolicyByPolicyNumber(String policyNumber) {
		InsurancePolicy policy = policyRepo.findByPolicyNumber(policyNumber);
		if (policy != null) {
			policyRepo.delete(policy);
			return true;
		}
		return false;
	}

	private InsurancePolicyResponseDto policyToPolicyResponseDto(InsurancePolicy policy) {
		InsurancePolicyResponseDto dto = new InsurancePolicyResponseDto();
		dto.setPolicyNumber(policy.getPolicyNumber());
		dto.setHolderName(policy.getHolderName());
		dto.setStartDate(policy.getStartDate());
		dto.setEndDate(policy.getEndDate());
		dto.setAmount(policy.getAmount());
		return dto;
	}

	private InsurancePolicy policyRequestDtoToPolicy(InsurancePolicyRequestDto policyDto) {
		InsurancePolicy policy = new InsurancePolicy();
		String policyNumber;
		do {
			policyNumber = "POL" + String.format("%03d", random.nextInt(1000));
		} while (policyRepo.findByPolicyNumber(policyNumber) != null);
		policy.setPolicyNumber(policyNumber);
		policy.setHolderName(policyDto.getHolderName());
		policy.setStartDate(policyDto.getStartDate());
		policy.setEndDate(policyDto.getEndDate());
		policy.setAmount(policyDto.getAmount());
		return policy;
	}
}