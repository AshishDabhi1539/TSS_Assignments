package com.tss.banking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AuditLogRequestDto {
    private String action;
    private Long entityId;
    private String performedBy;
}