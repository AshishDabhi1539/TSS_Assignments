package com.tss.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tss.banking.dto.request.KYCDocumentRequestDto;
import com.tss.banking.dto.response.KYCDocumentResponseDto;
import com.tss.banking.entity.User;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.KYCDocumentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/kyc-documents")
@Tag(name = "KYC Document", description = "KYC Document Management APIs")
public class KYCDocumentController {

    @Autowired
    private KYCDocumentService kycDocumentService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Operation(summary = "Create KYC document", description = "Create a new KYC document")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN', 'CUSTOMER')")
    public ResponseEntity<KYCDocumentResponseDto> createKYCDocument(@Valid @RequestBody KYCDocumentRequestDto dto) {
        return ResponseEntity.ok(kycDocumentService.createKYCDocument(dto));
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload KYC document with file", description = "Upload a KYC document with file to Cloudinary")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN', 'CUSTOMER')")
    public ResponseEntity<KYCDocumentResponseDto> uploadKYCDocument(
            @RequestParam Long customerId,
            @RequestParam String documentType,
            @RequestParam String documentNumber,
            @RequestParam String issuedBy,
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(kycDocumentService.uploadKYCDocument(customerId, documentType, documentNumber, issuedBy, file));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get KYC document by ID", description = "Retrieve KYC document details by ID")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN', 'CUSTOMER')")
    public ResponseEntity<KYCDocumentResponseDto> getKYCDocumentById(@PathVariable Long id) {
        return ResponseEntity.ok(kycDocumentService.getKYCDocumentById(id));
    }

    @GetMapping
    @Operation(summary = "Get all KYC documents", description = "Retrieve all KYC documents")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseEntity<List<KYCDocumentResponseDto>> getAllKYCDocuments() {
        return ResponseEntity.ok(kycDocumentService.getAllKYCDocuments());
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get KYC documents by customer", description = "Retrieve KYC documents for a specific customer")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN', 'CUSTOMER')")
    public ResponseEntity<List<KYCDocumentResponseDto>> getKYCDocumentsByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(kycDocumentService.getKYCDocumentsByCustomerId(customerId));
    }

    @GetMapping("/status")
    @Operation(summary = "Get KYC documents by status", description = "Retrieve KYC documents by verification status")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseEntity<List<KYCDocumentResponseDto>> getKYCDocumentsByStatus(@RequestParam String status) {
        return ResponseEntity.ok(kycDocumentService.getKYCDocumentsByStatus(status));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update KYC document", description = "Update an existing KYC document")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN', 'CUSTOMER')")
    public ResponseEntity<KYCDocumentResponseDto> updateKYCDocument(@PathVariable Long id, @Valid @RequestBody KYCDocumentRequestDto dto) {
        return ResponseEntity.ok(kycDocumentService.updateKYCDocument(id, dto));
    }

    @PutMapping("/{id}/verify")
    @Operation(summary = "Verify KYC document", description = "Verify a KYC document (Admin/SuperAdmin only)")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseEntity<KYCDocumentResponseDto> verifyKYCDocument(@PathVariable Long id, @RequestParam String status, Authentication authentication) {
        String email = authentication.getName();
        User verifier = userRepository.findByEmail(email).orElseThrow();
        return ResponseEntity.ok(kycDocumentService.verifyKYCDocument(id, status, verifier.getId()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete KYC document", description = "Delete a KYC document by ID")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseEntity<Void> deleteKYCDocument(@PathVariable Long id) {
        kycDocumentService.deleteKYCDocument(id);
        return ResponseEntity.noContent().build();
    }
}
