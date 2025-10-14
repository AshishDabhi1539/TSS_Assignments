package com.tss.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}