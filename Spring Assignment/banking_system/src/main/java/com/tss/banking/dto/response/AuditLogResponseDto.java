package com.tss.banking.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AuditLogResponseDto {
    private Long id;
    private String action;
    private Long entityId;
    private LocalDateTime timestamp;
    private String performedBy;
}