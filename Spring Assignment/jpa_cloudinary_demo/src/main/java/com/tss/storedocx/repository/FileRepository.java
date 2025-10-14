package com.tss.storedocx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tss.storedocx.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
