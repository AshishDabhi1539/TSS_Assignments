package com.tss.banking.service.impl;

<<<<<<< HEAD
import java.util.List;
=======
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tss.banking.dto.request.KYCDocumentRequestDto;
import com.tss.banking.dto.response.KYCDocumentResponseDto;
import com.tss.banking.entity.KYCDocument;
import com.tss.banking.entity.User;
import com.tss.banking.exception.BankApiException;
<<<<<<< HEAD
import com.tss.banking.repository.UserRepository;
=======
import com.tss.banking.repository.KYCDocumentRepository;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.CloudinaryService;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
import com.tss.banking.service.KYCDocumentService;

@Service
public class KYCDocumentServiceImpl implements KYCDocumentService {

    @Autowired
<<<<<<< HEAD
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
=======
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
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @Override
    public KYCDocumentResponseDto getKYCDocumentById(Long id) {
<<<<<<< HEAD
        throw new BankApiException("KYCDocumentRepository missing - cannot fetch document");
=======
        KYCDocument kycDocument = kycDocumentRepository.findById(id)
                .orElseThrow(() -> new BankApiException("KYC document not found with id: " + id));
        return mapToResponseDto(kycDocument);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @Override
    public List<KYCDocumentResponseDto> getAllKYCDocuments() {
<<<<<<< HEAD
        throw new BankApiException("KYCDocumentRepository missing - cannot list documents");
=======
        return kycDocumentRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @Override
    public List<KYCDocumentResponseDto> getKYCDocumentsByCustomerId(Long customerId) {
<<<<<<< HEAD
        throw new BankApiException("KYCDocumentRepository missing - cannot list documents by customer");
=======
        return kycDocumentRepository.findByCustomerId(customerId).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @Override
    public List<KYCDocumentResponseDto> getKYCDocumentsByStatus(String status) {
<<<<<<< HEAD
        throw new BankApiException("KYCDocumentRepository missing - cannot list documents by status");
=======
        return kycDocumentRepository.findByStatus(status).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @Override
    public KYCDocumentResponseDto updateKYCDocument(Long id, KYCDocumentRequestDto dto) {
<<<<<<< HEAD
        throw new BankApiException("KYCDocumentRepository missing - cannot update document");
    }

    @Override
    public KYCDocumentResponseDto verifyKYCDocument(Long id, String status, Long verifierId) {
        throw new BankApiException("KYCDocumentRepository missing - cannot verify document");
=======
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
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @Override
    public void deleteKYCDocument(Long id) {
<<<<<<< HEAD
        throw new BankApiException("KYCDocumentRepository missing - cannot delete document");
    }

    private String mask(String number) {
        if (number == null || number.length() < 4) return "****";
        String last4 = number.substring(number.length() - 4);
        return "****" + last4;
    }
}


=======
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
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
