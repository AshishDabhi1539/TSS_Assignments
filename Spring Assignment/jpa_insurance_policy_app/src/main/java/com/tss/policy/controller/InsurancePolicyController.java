package com.tss.policy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tss.policy.dto.InsurancePolicyRequestDto;
import com.tss.policy.dto.InsurancePolicyResponseDto;
import com.tss.policy.dto.InsurancePolicyResponsePage;
import com.tss.policy.entity.InsurancePolicy;
import com.tss.policy.service.InsurancePolicyService;

@RestController
@RequestMapping("/policyapp")
public class InsurancePolicyController {

	@Autowired
	private InsurancePolicyService policyService;

	@GetMapping("/policies")
	public ResponseEntity<InsurancePolicyResponsePage> readAllPolicies(@RequestParam(defaultValue = "5") int pageSize,
			@RequestParam(defaultValue = "0") int pageNo) {
		InsurancePolicyResponsePage policies = policyService.readAllPolicies(pageSize, pageNo);
		return ResponseEntity.ok(policies);
	}

	@GetMapping("/policies/search/holder")
	public ResponseEntity<InsurancePolicyResponsePage> searchByHolderName(@RequestParam String holderName,
			@RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "0") int pageNo) {
		InsurancePolicyResponsePage policies = policyService.searchByHolderName(holderName, pageSize, pageNo);
		return ResponseEntity.ok(policies);
	}

	@GetMapping("/policies/search/years")
	public ResponseEntity<InsurancePolicyResponsePage> searchByLessThanYears(@RequestParam int years,
			@RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "0") int pageNo) {
		InsurancePolicyResponsePage policies = policyService.searchByLessThanYears(years, pageSize, pageNo);
		return ResponseEntity.ok(policies);
	}

	@PostMapping("/policies")
	public ResponseEntity<InsurancePolicyResponseDto> addNewPolicy(@RequestBody InsurancePolicyRequestDto policy) {
		return ResponseEntity.ok().header("author", "Ashish").body(policyService.addNewPolicy(policy));
	}

	@GetMapping("/policies/{id}")
	public ResponseEntity<InsurancePolicy> readPolicyById(@PathVariable Long id) {
		Optional<InsurancePolicy> policy = policyService.readPolicyById(id);
		return policy.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/policies/search/number")
	public ResponseEntity<InsurancePolicyResponseDto> searchByPolicyNumber(@RequestParam String policyNumber) {
		InsurancePolicyResponseDto policy = policyService.searchByPolicyNumber(policyNumber);
		return policy != null ? ResponseEntity.ok(policy) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/policies/delete")
	public ResponseEntity<Void> deletePolicyByPolicyNumber(@RequestParam String policyNumber) {
	    boolean deleted = policyService.deletePolicyByPolicyNumber(policyNumber);
	    return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
}