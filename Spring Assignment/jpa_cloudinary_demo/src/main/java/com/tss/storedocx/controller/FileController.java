package com.tss.storedocx.controller;

import com.tss.storedocx.entity.FileEntity;
import com.tss.storedocx.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<FileEntity> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            log.info("API called: Upload file - {}", file.getOriginalFilename());
            return ResponseEntity.ok(fileService.uploadFile(file));
        } catch (Exception e) {
            log.error("File upload failed: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        try {
            log.info("API called: Delete file with id {}", id);
            return ResponseEntity.ok(fileService.deleteFile(id));
        } catch (Exception e) {
            log.error("File deletion failed for id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileEntity> getFile(@PathVariable Long id) {
        return ResponseEntity.ok(fileService.getFileById(id));
    }

    // ✅ New API to actually get the file (redirect to Cloudinary URL)
    @GetMapping("/{id}/view")
    public ResponseEntity<Void> viewFile(@PathVariable Long id) {
        String fileUrl = fileService.getFileUrl(id);
        log.info("Redirecting to file URL: {}", fileUrl);
        return ResponseEntity.status(302).location(URI.create(fileUrl)).build();
    }
}
