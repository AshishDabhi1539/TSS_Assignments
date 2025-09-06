package com.tss.sendmail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.sendmail.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}