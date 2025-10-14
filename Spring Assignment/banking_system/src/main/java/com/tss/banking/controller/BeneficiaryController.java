package com.tss.banking.controller;

<<<<<<< HEAD
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
=======
import java.util.List;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
<<<<<<< HEAD
import org.springframework.security.core.Authentication;
=======
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.request.BeneficiaryActivationRequestDto;
import com.tss.banking.dto.request.BeneficiaryApprovalRequestDto;
import com.tss.banking.dto.request.BeneficiaryCreateRequestDto;
import com.tss.banking.dto.request.BeneficiaryTransferRequestDto;
import com.tss.banking.dto.request.BeneficiaryUpdateRequestDto;
import com.tss.banking.dto.request.BeneficiaryValidationRequestDto;
import com.tss.banking.dto.response.BeneficiaryActivationResponseDto;
import com.tss.banking.dto.response.BeneficiaryResponseDto;
import com.tss.banking.dto.response.BeneficiaryValidationResponseDto;
=======
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.request.BeneficiaryRequestDto;
import com.tss.banking.dto.response.BeneficiaryResponseDto;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
import com.tss.banking.service.BeneficiaryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/beneficiaries")
@Tag(name = "Beneficiary", description = "Beneficiary Management APIs")
<<<<<<< HEAD
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_CUSTOMER')")
=======
@PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN', 'CUSTOMER')")
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;

    @PostMapping
<<<<<<< HEAD
    @Operation(summary = "Create beneficiary", description = "Create a new beneficiary for authenticated customer")
    public ResponseEntity<BeneficiaryResponseDto> createBeneficiary(
            @Valid @RequestBody BeneficiaryCreateRequestDto dto,
            Authentication authentication) {
        String customerEmail = authentication.getName();
        return ResponseEntity.ok(beneficiaryService.addBeneficiaryForCustomer(dto, customerEmail));
=======
    @Operation(summary = "Create beneficiary", description = "Create a new beneficiary")
    public ResponseEntity<BeneficiaryResponseDto> createBeneficiary(@Valid @RequestBody BeneficiaryRequestDto dto) {
        return ResponseEntity.ok(beneficiaryService.createBeneficiary(dto));
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get beneficiary by ID", description = "Retrieve beneficiary details by ID")
<<<<<<< HEAD
    public ResponseEntity<BeneficiaryResponseDto> getBeneficiaryById(@PathVariable Long id, Authentication authentication) {
        String customerEmail = authentication.getName();
        return ResponseEntity.ok(beneficiaryService.getBeneficiaryByIdForCustomer(id, customerEmail));
    }

    @GetMapping("/search")
    @Operation(summary = "Search beneficiaries", description = "Get beneficiaries by status or all beneficiaries for current customer")
    public ResponseEntity<List<BeneficiaryResponseDto>> searchBeneficiaries(
            @RequestParam(required = false) String status,
            Authentication authentication) {
        String customerEmail = authentication.getName();
        
        if (status != null) {
            return ResponseEntity.ok(beneficiaryService.getBeneficiariesByStatus(status, customerEmail));
        } else {
            return ResponseEntity.ok(beneficiaryService.getBeneficiariesByCustomerEmail(customerEmail));
        }
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get beneficiaries by status", description = "Get beneficiaries filtered by status")
    public ResponseEntity<List<BeneficiaryResponseDto>> getBeneficiariesByStatus(
            @PathVariable String status,
            Authentication authentication) {
        String customerEmail = authentication.getName();
        return ResponseEntity.ok(beneficiaryService.getBeneficiariesByStatus(status, customerEmail));
    }

    @GetMapping("/active")
    @Operation(summary = "Get active beneficiaries", description = "Get all active beneficiaries for current customer")
    public ResponseEntity<List<BeneficiaryResponseDto>> getActiveBeneficiaries(Authentication authentication) {
        String customerEmail = authentication.getName();
        return ResponseEntity.ok(beneficiaryService.getActiveBeneficiaries(customerEmail));
    }

    @GetMapping("/ready-for-activation")
    @Operation(summary = "Get beneficiaries ready for activation", description = "Get beneficiaries that have passed cooling period")
    public ResponseEntity<List<BeneficiaryResponseDto>> getBeneficiariesReadyForActivation(Authentication authentication) {
        String customerEmail = authentication.getName();
        return ResponseEntity.ok(beneficiaryService.getBeneficiariesReadyForActivationByCustomer(customerEmail));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update beneficiary", description = "Update beneficiary details")
    public ResponseEntity<BeneficiaryResponseDto> updateBeneficiary(
            @PathVariable Long id,
            @Valid @RequestBody BeneficiaryUpdateRequestDto dto,
            Authentication authentication) {
        String customerEmail = authentication.getName();
        return ResponseEntity.ok(beneficiaryService.updateBeneficiaryForCustomer(id, dto, customerEmail));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete beneficiary", description = "Delete a beneficiary by ID for current customer")
    public ResponseEntity<Map<String, String>> deleteBeneficiary(@PathVariable Long id, Authentication authentication) {
        String customerEmail = authentication.getName();
        beneficiaryService.deleteBeneficiaryForCustomer(id, customerEmail);
        
        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        response.put("message", "Beneficiary deleted successfully");
        response.put("beneficiaryId", id.toString());
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate")
    @Operation(summary = "Validate beneficiary", description = "Validate beneficiary details before adding")
    public ResponseEntity<BeneficiaryValidationResponseDto> validateBeneficiary(
            @Valid @RequestBody BeneficiaryValidationRequestDto request,
            Authentication authentication) {
        String customerEmail = authentication.getName();
        return ResponseEntity.ok(beneficiaryService.validateBeneficiary(request, customerEmail));
    }

    @PostMapping("/add-with-validation")
    @Operation(summary = "Add beneficiary with validation", description = "Add beneficiary with comprehensive validation - RECOMMENDED METHOD")
    public ResponseEntity<BeneficiaryResponseDto> addBeneficiaryWithValidation(
            @Valid @RequestBody BeneficiaryValidationRequestDto request,
            Authentication authentication) {
        String customerEmail = authentication.getName();
        return ResponseEntity.ok(beneficiaryService.addBeneficiaryWithValidation(request, customerEmail));
    }

    @PostMapping("/activate")
    @Operation(summary = "Activate beneficiary", description = "Activate beneficiary after cooling period")
    public ResponseEntity<BeneficiaryActivationResponseDto> activateBeneficiary(
            @Valid @RequestBody BeneficiaryActivationRequestDto request,
            Authentication authentication) {
        String customerEmail = authentication.getName();
        return ResponseEntity.ok(beneficiaryService.activateBeneficiary(request, customerEmail));
    }

    @PostMapping("/approve")
    @Operation(summary = "Approve beneficiary", description = "Approve or reject beneficiary (Admin only)")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    public ResponseEntity<BeneficiaryResponseDto> approveBeneficiary(
            @Valid @RequestBody BeneficiaryApprovalRequestDto request,
            Authentication authentication) {
        String approverEmail = authentication.getName();
        return ResponseEntity.ok(beneficiaryService.approveBeneficiary(request, approverEmail));
    }

    @PostMapping("/transfer")
    @Operation(summary = "Transfer to beneficiary", description = "Transfer funds to beneficiary")
    public ResponseEntity<BeneficiaryResponseDto> transferToBeneficiary(
            @Valid @RequestBody BeneficiaryTransferRequestDto request,
            Authentication authentication) {
        String customerEmail = authentication.getName();
        return ResponseEntity.ok(beneficiaryService.transferToBeneficiary(request, customerEmail));
    }

    @PostMapping("/process-cooling-period")
    @Operation(summary = "Process cooling period expiry", description = "Process beneficiaries ready for activation")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    public ResponseEntity<Void> processCoolingPeriodExpiry() {
        beneficiaryService.processCoolingPeriodExpiry();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-daily-limits")
    @Operation(summary = "Reset daily transfer limits", description = "Reset daily transfer limits for all beneficiaries")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    public ResponseEntity<Void> resetDailyTransferLimits() {
        beneficiaryService.resetDailyTransferLimits();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-monthly-limits")
    @Operation(summary = "Reset monthly transfer limits", description = "Reset monthly transfer limits for all beneficiaries")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    public ResponseEntity<Void> resetMonthlyTransferLimits() {
        beneficiaryService.resetMonthlyTransferLimits();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pending-approval")
    @Operation(summary = "Get beneficiaries pending approval", description = "Get all beneficiaries pending admin approval")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BeneficiaryResponseDto>> getBeneficiariesPendingApproval() {
        List<BeneficiaryResponseDto> beneficiaries = beneficiaryService.getBeneficiariesByApprovalStatus("PENDING");
        return ResponseEntity.ok(beneficiaries);
    }

    @PostMapping("/approve/{id}")
    @Operation(summary = "Approve beneficiary by ID", description = "Approve or reject a specific beneficiary")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    public ResponseEntity<BeneficiaryResponseDto> approveBeneficiaryById(
            @PathVariable Long id,
            @RequestBody BeneficiaryApprovalRequestDto request,
            Authentication authentication) {
        BeneficiaryResponseDto beneficiary = beneficiaryService.approveBeneficiary(request, authentication.getName());
        return ResponseEntity.ok(beneficiary);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all beneficiaries", description = "Get all beneficiaries (Admin only)")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
=======
    public ResponseEntity<BeneficiaryResponseDto> getBeneficiaryById(@PathVariable Long id) {
        return ResponseEntity.ok(beneficiaryService.getBeneficiaryById(id));
    }

    @GetMapping
    @Operation(summary = "Get all beneficiaries", description = "Retrieve all beneficiaries")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    public ResponseEntity<List<BeneficiaryResponseDto>> getAllBeneficiaries() {
        return ResponseEntity.ok(beneficiaryService.getAllBeneficiaries());
    }

<<<<<<< HEAD
    @GetMapping("/dormant")
    @Operation(summary = "Get dormant beneficiaries", description = "Get beneficiaries inactive for 90+ days (Admin only)")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    public ResponseEntity<List<BeneficiaryResponseDto>> getDormantBeneficiaries() {
        return ResponseEntity.ok(beneficiaryService.getDormantBeneficiaries());
    }

    @PostMapping("/process-dormant")
    @Operation(summary = "Process dormant beneficiaries", description = "Mark inactive beneficiaries as dormant (Admin only)")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    public ResponseEntity<Void> processDormantBeneficiaries() {
        beneficiaryService.processDormantBeneficiaries();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/generate-transfer-otp")
    @Operation(summary = "Generate OTP for transfer", description = "Generate smart OTP based on transfer amount and beneficiary type")
    public ResponseEntity<Map<String, String>> generateTransferOtp(
            @RequestParam Long beneficiaryId,
            @RequestParam BigDecimal amount,
            Authentication authentication) {
        String customerEmail = authentication.getName();
        Map<String, String> response = new HashMap<>();
        try {
            String message = beneficiaryService.generateTransferOtp(customerEmail, beneficiaryId, amount);
            response.put("success", "true");
            response.put("message", message);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", "false");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
=======
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
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }
}
