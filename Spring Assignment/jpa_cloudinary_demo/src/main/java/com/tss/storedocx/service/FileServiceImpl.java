package com.tss.storedocx.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tss.storedocx.entity.FileEntity;
import com.tss.storedocx.repository.FileRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    private final Cloudinary cloudinary;
    private final FileRepository fileRepository;

    @Override
    public FileEntity uploadFile(MultipartFile file) throws IOException {
        log.info("Uploading file: {}", file.getOriginalFilename());

        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap("resource_type", "auto") // auto → handles images, pdfs, videos
        );

        FileEntity fileEntity = FileEntity.builder()
                .fileName(file.getOriginalFilename())
                .fileUrl(uploadResult.get("secure_url").toString())
                .publicId(uploadResult.get("public_id").toString())
                .fileType(file.getContentType())
                .fileSize(file.getSize())
                .build();

        FileEntity savedFile = fileRepository.save(fileEntity);

        log.info("File uploaded successfully: {} | URL: {}", savedFile.getFileName(), savedFile.getFileUrl());
        return savedFile;
    }

    @Override
    public String deleteFile(Long id) throws IOException {
        log.info("Deleting file with id: {}", id);

        FileEntity file = fileRepository.findById(id).orElseThrow(() -> {
            log.error("File not found with id: {}", id);
            return new RuntimeException("File not found with id: " + id);
        });

        cloudinary.uploader().destroy(file.getPublicId(), ObjectUtils.asMap("resource_type", "auto"));
        fileRepository.delete(file);

        log.info("File deleted successfully: {}", file.getFileName());
        return "File deleted successfully";
    }

    @Override
    public FileEntity getFileById(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id: " + id));
    }

    @Override
    public String getFileUrl(Long id) {
        FileEntity file = getFileById(id);
        return file.getFileUrl(); // ✅ return Cloudinary URL
    }
}
