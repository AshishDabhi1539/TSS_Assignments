package com.tss.sendmail.service;

import com.tss.sendmail.entity.FileEntity;

public interface FileService {

	FileEntity saveFile(String fileName, String contentType, Long fileSize, byte[] fileData);
	
	FileEntity getFile(Long id);
}
