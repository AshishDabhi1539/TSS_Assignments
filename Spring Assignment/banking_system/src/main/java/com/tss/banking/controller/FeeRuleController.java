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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.request.FeeRuleRequestDto;
import com.tss.banking.dto.response.FeeRuleResponseDto;
import com.tss.banking.service.FeeRuleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/fee-rules")
@Tag(name = "Fee Rule", description = "Fee Rule Management APIs")
@PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
public class FeeRuleController {

    @Autowired
    private FeeRuleService feeRuleService;

    @PostMapping
    @Operation(summary = "Create fee rule", description = "Create a new fee rule")
    public ResponseEntity<FeeRuleResponseDto> createFeeRule(@Valid @RequestBody FeeRuleRequestDto dto) {
        return ResponseEntity.ok(feeRuleService.createFeeRule(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get fee rule by ID", description = "Retrieve fee rule details by ID")
    public ResponseEntity<FeeRuleResponseDto> getFeeRuleById(@PathVariable Long id) {
        return ResponseEntity.ok(feeRuleService.getFeeRuleById(id));
    }

    @GetMapping
    @Operation(summary = "Get all fee rules", description = "Retrieve all fee rules")
    public ResponseEntity<List<FeeRuleResponseDto>> getAllFeeRules() {
        return ResponseEntity.ok(feeRuleService.getAllFeeRules());
    }

    @GetMapping("/active")
    @Operation(summary = "Get active fee rules", description = "Retrieve all active fee rules")
    public ResponseEntity<List<FeeRuleResponseDto>> getActiveFeeRules() {
        return ResponseEntity.ok(feeRuleService.getActiveFeeRules());
    }

    @GetMapping("/transaction-type")
    @Operation(summary = "Get fee rules by transaction type", description = "Retrieve fee rules for a specific transaction type")
    public ResponseEntity<List<FeeRuleResponseDto>> getFeeRulesByTransactionType(@RequestParam String transactionType) {
        return ResponseEntity.ok(feeRuleService.getFeeRulesByTransactionType(transactionType));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update fee rule", description = "Update an existing fee rule")
    public ResponseEntity<FeeRuleResponseDto> updateFeeRule(@PathVariable Long id, @Valid @RequestBody FeeRuleRequestDto dto) {
        return ResponseEntity.ok(feeRuleService.updateFeeRule(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete fee rule", description = "Delete a fee rule by ID")
    public ResponseEntity<Void> deleteFeeRule(@PathVariable Long id) {
        feeRuleService.deleteFeeRule(id);
        return ResponseEntity.noContent().build();
    }
}
