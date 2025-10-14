package com.tss.banking.service;

import com.tss.banking.dto.request.AuditLogRequestDto;
import com.tss.banking.dto.response.AuditLogResponseDto;

public interface AuditLogService {
    AuditLogResponseDto logAction(AuditLogRequestDto dto);
    AuditLogResponseDto getLogById(Long id);
}