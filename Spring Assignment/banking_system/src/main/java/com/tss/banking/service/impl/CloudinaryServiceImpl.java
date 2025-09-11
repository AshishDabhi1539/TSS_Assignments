package com.tss.banking.service.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.service.CloudinaryService;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Map<String, Object> uploadFile(MultipartFile file) throws IOException {
        return uploadFile(file, "banking_documents");
    }

    @Override
    public Map<String, Object> uploadFile(MultipartFile file, String folder) throws IOException {
        try {
            // Validate file
            if (file.isEmpty()) {
                throw new BankApiException("File is empty");
            }

            // Check file size (max 10MB)
            if (file.getSize() > 10 * 1024 * 1024) {
                throw new BankApiException("File size exceeds 10MB limit");
            }

            // Check file type
            String contentType = file.getContentType();
            if (contentType == null || (!contentType.startsWith("image/") && 
                !contentType.equals("application/pdf") && 
                !contentType.startsWith("application/msword") &&
                !contentType.startsWith("application/vnd.openxmlformats-officedocument"))) {
                throw new BankApiException("Invalid file type. Only images, PDF, and Word documents are allowed");
            }

            Map<String, Object> uploadParams = ObjectUtils.asMap(
                "folder", folder,
                "resource_type", "auto",
                "use_filename", true,
                "unique_filename", true,
                "overwrite", false
            );

            return cloudinary.uploader().upload(file.getBytes(), uploadParams);
        } catch (IOException e) {
            throw new BankApiException("Failed to upload file to Cloudinary: " + e.getMessage());
        }
    }

    @Override
    public void deleteFile(String publicId) throws IOException {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new BankApiException("Failed to delete file from Cloudinary: " + e.getMessage());
        }
    }

    @Override
    public String getFileUrl(String publicId) {
        return cloudinary.url().generate(publicId);
    }
}
