package com.tss.storedocx.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.tss.storedocx.entity.FileEntity;

public interface FileService {
    FileEntity uploadFile(MultipartFile file) throws IOException;

    String deleteFile(Long id) throws IOException;

    FileEntity getFileById(Long id);

    String getFileUrl(Long id); // ✅ new method to return actual URL
}
