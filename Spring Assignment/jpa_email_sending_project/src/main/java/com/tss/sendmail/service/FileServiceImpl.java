package com.tss.sendmail.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.sendmail.entity.FileEntity;
import com.tss.sendmail.repository.FileRepository;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileRepository fileRepository;

	@Override
	public FileEntity saveFile(String fileName, String contentType, Long fileSize, byte[] fileData) {
		FileEntity file = new FileEntity();
		file.setFileName(fileName);
		file.setContentType(contentType);
		file.setFileSize(fileSize);
		file.setFileData(fileData);
		return fileRepository.save(file);
	}
	
	@Override
    public FileEntity getFile(Long id) {
        Optional<FileEntity> optionalFile = fileRepository.findById(id);
        return optionalFile.orElse(null);
    }
}
