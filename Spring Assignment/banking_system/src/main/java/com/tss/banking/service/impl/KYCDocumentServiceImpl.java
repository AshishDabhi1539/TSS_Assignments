package com.tss.banking.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tss.banking.dto.request.KYCDocumentRequestDto;
import com.tss.banking.dto.response.KYCDocumentResponseDto;
import com.tss.banking.entity.KYCDocument;
import com.tss.banking.entity.User;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.KYCDocumentRepository;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.CloudinaryService;
import com.tss.banking.service.KYCDocumentService;

@Service
public class KYCDocumentServiceImpl implements KYCDocumentService {

    @Autowired
    private KYCDocumentRepository kycDocumentRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public KYCDocumentResponseDto createKYCDocument(KYCDocumentRequestDto dto) {
        User customer = userRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new BankApiException("Customer not found with id: " + dto.getCustomerId()));

        KYCDocument kycDocument = new KYCDocument();
        kycDocument.setCustomer(customer);
        kycDocument.setDocumentType(dto.getDocumentType());
        kycDocument.setDocumentNumberMasked(maskDocumentNumber(dto.getDocumentNumber()));
        kycDocument.setStatus(dto.getStatus() != null ? dto.getStatus() : "PENDING");
        kycDocument.setIssuedBy(dto.getIssuedBy());
        kycDocument.setExpiryDate(dto.getExpiryDate());
        kycDocument.setFileRef(dto.getFileRef());

        KYCDocument savedDocument = kycDocumentRepository.save(kycDocument);
        return mapToResponseDto(savedDocument);
    }

    @Override
    public KYCDocumentResponseDto uploadKYCDocument(Long customerId, String documentType, 
                                                   String documentNumber, String issuedBy, 
                                                   MultipartFile file) {
        try {
            User customer = userRepository.findById(customerId)
                    .orElseThrow(() -> new BankApiException("Customer not found with id: " + customerId));

            // Upload file to Cloudinary
            Map<String, Object> uploadResult = cloudinaryService.uploadFile(file, "kyc_documents");
            String fileRef = (String) uploadResult.get("public_id");

            KYCDocument kycDocument = new KYCDocument();
            kycDocument.setCustomer(customer);
            kycDocument.setDocumentType(documentType);
            kycDocument.setDocumentNumberMasked(maskDocumentNumber(documentNumber));
            kycDocument.setStatus("PENDING");
            kycDocument.setIssuedBy(issuedBy);
            kycDocument.setFileRef(fileRef);

            KYCDocument savedDocument = kycDocumentRepository.save(kycDocument);
            return mapToResponseDto(savedDocument);
        } catch (IOException e) {
            throw new BankApiException("Failed to upload KYC document: " + e.getMessage());
        }
    }

    @Override
    public KYCDocumentResponseDto getKYCDocumentById(Long id) {
        KYCDocument kycDocument = kycDocumentRepository.findById(id)
                .orElseThrow(() -> new BankApiException("KYC document not found with id: " + id));
        return mapToResponseDto(kycDocument);
    }

    @Override
    public List<KYCDocumentResponseDto> getAllKYCDocuments() {
        return kycDocumentRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<KYCDocumentResponseDto> getKYCDocumentsByCustomerId(Long customerId) {
        return kycDocumentRepository.findByCustomerId(customerId).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<KYCDocumentResponseDto> getKYCDocumentsByStatus(String status) {
        return kycDocumentRepository.findByStatus(status).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public KYCDocumentResponseDto updateKYCDocument(Long id, KYCDocumentRequestDto dto) {
        KYCDocument kycDocument = kycDocumentRepository.findById(id)
                .orElseThrow(() -> new BankApiException("KYC document not found with id: " + id));

        if (dto.getDocumentType() != null) {
            kycDocument.setDocumentType(dto.getDocumentType());
        }
        if (dto.getDocumentNumber() != null) {
            kycDocument.setDocumentNumberMasked(maskDocumentNumber(dto.getDocumentNumber()));
        }
        if (dto.getStatus() != null) {
            kycDocument.setStatus(dto.getStatus());
        }
        if (dto.getIssuedBy() != null) {
            kycDocument.setIssuedBy(dto.getIssuedBy());
        }
        if (dto.getExpiryDate() != null) {
            kycDocument.setExpiryDate(dto.getExpiryDate());
        }
        if (dto.getFileRef() != null) {
            kycDocument.setFileRef(dto.getFileRef());
        }

        KYCDocument updatedDocument = kycDocumentRepository.save(kycDocument);
        return mapToResponseDto(updatedDocument);
    }

    @Override
    public KYCDocumentResponseDto verifyKYCDocument(Long id, String status, Long verifiedBy) {
        KYCDocument kycDocument = kycDocumentRepository.findById(id)
                .orElseThrow(() -> new BankApiException("KYC document not found with id: " + id));

        kycDocument.setStatus(status);

        KYCDocument updatedDocument = kycDocumentRepository.save(kycDocument);
        return mapToResponseDto(updatedDocument);
    }

    @Override
    public void deleteKYCDocument(Long id) {
        if (!kycDocumentRepository.existsById(id)) {
            throw new BankApiException("KYC document not found with id: " + id);
        }
        kycDocumentRepository.deleteById(id);
    }

    private KYCDocumentResponseDto mapToResponseDto(KYCDocument kycDocument) {
        KYCDocumentResponseDto dto = new KYCDocumentResponseDto();
        dto.setId(kycDocument.getId());
        dto.setCustomerId(kycDocument.getCustomer().getId());
        dto.setDocumentType(kycDocument.getDocumentType());
        dto.setDocumentNumberMasked(kycDocument.getDocumentNumberMasked());
        dto.setStatus(kycDocument.getStatus());
        dto.setIssuedBy(kycDocument.getIssuedBy());
        dto.setExpiryDate(kycDocument.getExpiryDate());
        dto.setFileRef(kycDocument.getFileRef());
        dto.setCreatedAt(kycDocument.getCreatedAt());
        
        // Generate file URL if fileRef exists
        if (kycDocument.getFileRef() != null) {
            dto.setFileUrl(cloudinaryService.getFileUrl(kycDocument.getFileRef()));
        }
        
        return dto;
    }
    
    private String maskDocumentNumber(String documentNumber) {
        if (documentNumber == null || documentNumber.length() <= 4) {
            return documentNumber;
        }
        
        int visibleChars = 4;
        String masked = "*".repeat(documentNumber.length() - visibleChars);
        return masked + documentNumber.substring(documentNumber.length() - visibleChars);
    }
}
