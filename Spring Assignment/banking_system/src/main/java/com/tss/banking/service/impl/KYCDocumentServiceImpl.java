package com.tss.banking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tss.banking.dto.request.KYCDocumentRequestDto;
import com.tss.banking.dto.response.KYCDocumentResponseDto;
import com.tss.banking.entity.KYCDocument;
import com.tss.banking.entity.User;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.KYCDocumentService;

@Service
public class KYCDocumentServiceImpl implements KYCDocumentService {

    @Autowired
    private UserRepository userRepository;

    // ModelMapper not used in stub implementation

    @Override
    public KYCDocumentResponseDto createKYCDocument(KYCDocumentRequestDto dto) {
        // Minimal stub until DTO fields are defined
        throw new BankApiException("Not supported: use /upload endpoint with file");
    }

    @Override
    public KYCDocumentResponseDto uploadKYCDocument(Long customerId, String documentType, String documentNumber,
            String issuedBy, MultipartFile file) {
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new BankApiException("Customer not found with ID: " + customerId));

        KYCDocument doc = new KYCDocument();
        doc.setCustomer(customer);
        doc.setDocumentType(documentType);
        doc.setDocumentNumberMasked(mask(documentNumber));
        doc.setIssuedBy(issuedBy);
        doc.setStatus("PENDING");
        // For demo, store original filename as fileRef
        doc.setFileRef(file != null ? file.getOriginalFilename() : null);

        // Persist using a simple approach (no repository provided in tree)
        // In absence of KYCDocumentRepository, we throw to avoid silent failure
        throw new BankApiException("KYCDocumentRepository missing - cannot persist document");
    }

    @Override
    public KYCDocumentResponseDto getKYCDocumentById(Long id) {
        throw new BankApiException("KYCDocumentRepository missing - cannot fetch document");
    }

    @Override
    public List<KYCDocumentResponseDto> getAllKYCDocuments() {
        throw new BankApiException("KYCDocumentRepository missing - cannot list documents");
    }

    @Override
    public List<KYCDocumentResponseDto> getKYCDocumentsByCustomerId(Long customerId) {
        throw new BankApiException("KYCDocumentRepository missing - cannot list documents by customer");
    }

    @Override
    public List<KYCDocumentResponseDto> getKYCDocumentsByStatus(String status) {
        throw new BankApiException("KYCDocumentRepository missing - cannot list documents by status");
    }

    @Override
    public KYCDocumentResponseDto updateKYCDocument(Long id, KYCDocumentRequestDto dto) {
        throw new BankApiException("KYCDocumentRepository missing - cannot update document");
    }

    @Override
    public KYCDocumentResponseDto verifyKYCDocument(Long id, String status, Long verifierId) {
        throw new BankApiException("KYCDocumentRepository missing - cannot verify document");
    }

    @Override
    public void deleteKYCDocument(Long id) {
        throw new BankApiException("KYCDocumentRepository missing - cannot delete document");
    }

    private String mask(String number) {
        if (number == null || number.length() < 4) return "****";
        String last4 = number.substring(number.length() - 4);
        return "****" + last4;
    }
}


