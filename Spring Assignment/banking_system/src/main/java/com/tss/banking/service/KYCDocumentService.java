package com.tss.banking.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tss.banking.dto.request.KYCDocumentRequestDto;
import com.tss.banking.dto.response.KYCDocumentResponseDto;

public interface KYCDocumentService {
<<<<<<< HEAD
    KYCDocumentResponseDto createKYCDocument(KYCDocumentRequestDto dto);
    KYCDocumentResponseDto uploadKYCDocument(Long customerId, String documentType, String documentNumber, String issuedBy, MultipartFile file);
    KYCDocumentResponseDto getKYCDocumentById(Long id);
    List<KYCDocumentResponseDto> getAllKYCDocuments();
    List<KYCDocumentResponseDto> getKYCDocumentsByCustomerId(Long customerId);
    List<KYCDocumentResponseDto> getKYCDocumentsByStatus(String status);
    KYCDocumentResponseDto updateKYCDocument(Long id, KYCDocumentRequestDto dto);
    KYCDocumentResponseDto verifyKYCDocument(Long id, String status, Long verifierId);
    void deleteKYCDocument(Long id);
}


=======
    
    KYCDocumentResponseDto createKYCDocument(KYCDocumentRequestDto dto);
    
    KYCDocumentResponseDto uploadKYCDocument(Long customerId, String documentType, 
                                           String documentNumber, String issuedBy, 
                                           MultipartFile file);
    
    KYCDocumentResponseDto getKYCDocumentById(Long id);
    
    List<KYCDocumentResponseDto> getAllKYCDocuments();
    
    List<KYCDocumentResponseDto> getKYCDocumentsByCustomerId(Long customerId);
    
    List<KYCDocumentResponseDto> getKYCDocumentsByStatus(String status);
    
    KYCDocumentResponseDto updateKYCDocument(Long id, KYCDocumentRequestDto dto);
    
    KYCDocumentResponseDto verifyKYCDocument(Long id, String status, Long verifiedBy);
    
    void deleteKYCDocument(Long id);
}
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
