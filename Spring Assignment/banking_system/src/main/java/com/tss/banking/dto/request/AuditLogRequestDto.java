package com.tss.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AuditLogRequestDto {
    
    @NotBlank(message = "Action is required")
    @Size(max = 100, message = "Action cannot exceed 100 characters")
    private String action;
    
    @NotNull(message = "Entity ID is required")
    private Long entityId;
    
    @NotBlank(message = "Performed by is required")
    @Size(max = 100, message = "Performed by cannot exceed 100 characters")
    private String performedBy;
}