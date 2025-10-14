package com.tss.sendmail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tss.sendmail.entity.FileEntity;
import com.tss.sendmail.service.FileService;

@RestController
@RequestMapping("/upload")
public class FileController {

	@Autowired
	private FileService fileService;

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	private static int fileUploadCount = 0;

	@PostMapping("/upload-files")
	public ResponseEntity<String> uploadFiles(@RequestParam("files") MultipartFile[] files) {

		try {
			for (MultipartFile file : files) {
				if (!file.isEmpty()) {
					String fileName = file.getOriginalFilename();
					String contentType = file.getContentType();
					Long fileSize = file.getSize();
					byte[] fileData = file.getBytes();

					FileEntity savedFile = fileService.saveFile(fileName, contentType, fileSize, fileData);

					fileUploadCount++;

					long fileSizeKB = file.getSize() / 1024;

					logger.info(fileUploadCount + " File uploaded | File Size: " + fileSizeKB + " KB | File Name: "
							+ file.getOriginalFilename());
				}
			}

			logger.info(fileUploadCount + " Files uploaded Successfully.");

			String message = fileUploadCount + " Files Uploaded";

			return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.info("Files not uploaded !! Try Again !!");
			String errorMessage = "File upload failed: " + e.getMessage();
			logger.error(errorMessage, e);
			return new ResponseEntity<>(errorMessage, HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        FileEntity fileEntity = fileService.getFile(id);

        if (fileEntity == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "\"")
                .contentType(MediaType.parseMediaType(fileEntity.getContentType()))
                .body(fileEntity.getFileData());
    }
}