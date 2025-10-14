package com.tss.storedocx.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.tss.storedocx.entity.FileEntity;
import com.tss.storedocx.repository.FileRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    private final WebClient webClient;
    private final FileRepository fileRepository;
    
    @Value("${supabase.url}")
    private String supabaseUrl;
    
    @Value("${supabase.service.key}")
    private String serviceKey;
    
    @Value("${supabase.bucket.name}")
    private String bucketName;

    @Override
    public FileEntity uploadFile(MultipartFile file) throws IOException {
        log.info("Uploading file to Supabase: {}", file.getOriginalFilename());

        // Create a unique filename
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName != null && originalFileName.contains(".") 
            ? originalFileName.substring(originalFileName.lastIndexOf(".")) : "";
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
        
        try {
            // Upload to Supabase Storage using REST API
            String uploadUrl = supabaseUrl + "/storage/v1/object/" + bucketName + "/" + uniqueFileName;
            
            webClient.post()
                .uri(uploadUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + serviceKey)
                .header(HttpHeaders.CONTENT_TYPE, file.getContentType())
                .body(BodyInserters.fromResource(new ByteArrayResource(file.getBytes())))
                .retrieve()
                .bodyToMono(String.class)
                .block();
            
            // Generate public URL
            String fileUrl = supabaseUrl + "/storage/v1/object/public/" + bucketName + "/" + uniqueFileName;

            FileEntity fileEntity = FileEntity.builder()
                    .fileName(file.getOriginalFilename())
                    .fileUrl(fileUrl)
                    .publicId(uniqueFileName)
                    .fileType(file.getContentType())
                    .fileSize(file.getSize())
                    .build();

            FileEntity savedFile = fileRepository.save(fileEntity);

            log.info("File uploaded successfully to Supabase: {} | URL: {}", savedFile.getFileName(), savedFile.getFileUrl());
            return savedFile;
            
        } catch (Exception e) {
            log.error("Failed to upload file to Supabase: {}", e.getMessage());
            throw new IOException("File upload failed: " + e.getMessage());
        }
    }

    @Override
    public String deleteFile(Long id) throws IOException {
        log.info("Deleting file from Supabase with id: {}", id);

        FileEntity file = fileRepository.findById(id).orElseThrow(() -> {
            log.error("File not found with id: {}", id);
            return new RuntimeException("File not found with id: " + id);
        });

        try {
            // Delete from Supabase Storage
            String deleteUrl = supabaseUrl + "/storage/v1/object/" + bucketName + "/" + file.getPublicId();
            
            webClient.delete()
                .uri(deleteUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + serviceKey)
                .retrieve()
                .bodyToMono(String.class)
                .block();
            
            fileRepository.delete(file);

            log.info("File deleted successfully from Supabase: {}", file.getFileName());
            return "File deleted successfully";
            
        } catch (Exception e) {
            log.error("Failed to delete file from Supabase: {}", e.getMessage());
            throw new IOException("File deletion failed: " + e.getMessage());
        }
    }

    @Override
    public FileEntity getFileById(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id: " + id));
    }

    @Override
    public String getFileUrl(Long id) {
        FileEntity file = getFileById(id);
        return file.getFileUrl(); // ✅ return S3 URL
    }
}
