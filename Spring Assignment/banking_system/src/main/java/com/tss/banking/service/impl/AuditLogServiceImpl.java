package com.tss.banking.service.impl;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.banking.dto.request.AuditLogRequestDto;
import com.tss.banking.dto.response.AuditLogResponseDto;
import com.tss.banking.entity.AuditLog;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.AuditLogRepository;
import com.tss.banking.service.AuditLogService;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public AuditLogResponseDto logAction(AuditLogRequestDto dto) {
        AuditLog auditLog = mapper.map(dto, AuditLog.class);
        auditLog.setTimestamp(LocalDateTime.now());
        AuditLog savedLog = auditLogRepo.save(auditLog);
        return mapper.map(savedLog, AuditLogResponseDto.class);
    }

    @Override
    public AuditLogResponseDto getLogById(Long id) {
        AuditLog auditLog = auditLogRepo.findById(id)
                .orElseThrow(() -> new BankApiException("Audit log not found with ID: " + id));
        return mapper.map(auditLog, AuditLogResponseDto.class);
    }
}