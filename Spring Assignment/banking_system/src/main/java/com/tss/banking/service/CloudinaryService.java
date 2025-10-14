package com.tss.banking.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    
    Map<String, Object> uploadFile(MultipartFile file) throws IOException;
    
    Map<String, Object> uploadFile(MultipartFile file, String folder) throws IOException;
    
    void deleteFile(String publicId) throws IOException;
    
    String getFileUrl(String publicId);
}
