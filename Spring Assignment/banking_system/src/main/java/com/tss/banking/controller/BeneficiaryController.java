package com.tss.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.request.BeneficiaryRequestDto;
import com.tss.banking.dto.response.BeneficiaryResponseDto;
import com.tss.banking.service.BeneficiaryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/beneficiaries")
@Tag(name = "Beneficiary", description = "Beneficiary Management APIs")
@PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN', 'CUSTOMER')")
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;

    @PostMapping
    @Operation(summary = "Create beneficiary", description = "Create a new beneficiary")
    public ResponseEntity<BeneficiaryResponseDto> createBeneficiary(@Valid @RequestBody BeneficiaryRequestDto dto) {
        return ResponseEntity.ok(beneficiaryService.createBeneficiary(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get beneficiary by ID", description = "Retrieve beneficiary details by ID")
    public ResponseEntity<BeneficiaryResponseDto> getBeneficiaryById(@PathVariable Long id) {
        return ResponseEntity.ok(beneficiaryService.getBeneficiaryById(id));
    }

    @GetMapping
    @Operation(summary = "Get all beneficiaries", description = "Retrieve all beneficiaries")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseEntity<List<BeneficiaryResponseDto>> getAllBeneficiaries() {
        return ResponseEntity.ok(beneficiaryService.getAllBeneficiaries());
    }

    @GetMapping("/owner/{ownerId}")
    @Operation(summary = "Get beneficiaries by owner", description = "Retrieve beneficiaries for a specific owner")
    public ResponseEntity<List<BeneficiaryResponseDto>> getBeneficiariesByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(beneficiaryService.getBeneficiariesByOwner(ownerId));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update beneficiary", description = "Update an existing beneficiary")
    public ResponseEntity<BeneficiaryResponseDto> updateBeneficiary(@PathVariable Long id, @Valid @RequestBody BeneficiaryRequestDto dto) {
        return ResponseEntity.ok(beneficiaryService.updateBeneficiary(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete beneficiary", description = "Delete a beneficiary by ID")
    public ResponseEntity<Void> deleteBeneficiary(@PathVariable Long id) {
        beneficiaryService.deleteBeneficiary(id);
        return ResponseEntity.noContent().build();
    }
}
