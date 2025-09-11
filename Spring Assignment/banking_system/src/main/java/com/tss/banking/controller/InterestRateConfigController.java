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

import com.tss.banking.dto.request.InterestRateConfigRequestDto;
import com.tss.banking.dto.response.InterestRateConfigResponseDto;
import com.tss.banking.service.InterestRateConfigService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/interest-rate-configs")
@Tag(name = "Interest Rate Config", description = "Interest Rate Configuration Management APIs")
@PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
public class InterestRateConfigController {

    @Autowired
    private InterestRateConfigService interestRateConfigService;

    @PostMapping
    @Operation(summary = "Create interest rate config", description = "Create a new interest rate configuration")
    public ResponseEntity<InterestRateConfigResponseDto> createInterestRateConfig(@Valid @RequestBody InterestRateConfigRequestDto dto) {
        return ResponseEntity.ok(interestRateConfigService.createInterestRateConfig(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get interest rate config by ID", description = "Retrieve interest rate config details by ID")
    public ResponseEntity<InterestRateConfigResponseDto> getInterestRateConfigById(@PathVariable Long id) {
        return ResponseEntity.ok(interestRateConfigService.getInterestRateConfigById(id));
    }

    @GetMapping
    @Operation(summary = "Get all interest rate configs", description = "Retrieve all interest rate configurations")
    public ResponseEntity<List<InterestRateConfigResponseDto>> getAllInterestRateConfigs() {
        return ResponseEntity.ok(interestRateConfigService.getAllInterestRateConfigs());
    }

    @GetMapping("/active")
    @Operation(summary = "Get active interest rate configs", description = "Retrieve all active interest rate configurations")
    public ResponseEntity<List<InterestRateConfigResponseDto>> getActiveInterestRateConfigs() {
        return ResponseEntity.ok(interestRateConfigService.getActiveInterestRateConfigs());
    }

    @GetMapping("/account-type")
    @Operation(summary = "Get interest rate configs by account type", description = "Retrieve interest rate configs for a specific account type")
    public ResponseEntity<List<InterestRateConfigResponseDto>> getInterestRateConfigsByAccountType(@RequestParam String accountType) {
        return ResponseEntity.ok(interestRateConfigService.getInterestRateConfigsByAccountType(accountType));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update interest rate config", description = "Update an existing interest rate configuration")
    public ResponseEntity<InterestRateConfigResponseDto> updateInterestRateConfig(@PathVariable Long id, @Valid @RequestBody InterestRateConfigRequestDto dto) {
        return ResponseEntity.ok(interestRateConfigService.updateInterestRateConfig(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete interest rate config", description = "Delete an interest rate configuration by ID")
    public ResponseEntity<Void> deleteInterestRateConfig(@PathVariable Long id) {
        interestRateConfigService.deleteInterestRateConfig(id);
        return ResponseEntity.noContent().build();
    }
}
