package com.tss.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogRequestDto {
    
    @NotBlank(message = "Action is required")
    @Size(max = 64, message = "Action cannot exceed 64 characters")
    private String action;
    
    @NotNull(message = "Entity ID is required")
    private Long entityId;
    
    @NotBlank(message = "Performed by is required")
    @Size(max = 64, message = "Performed by cannot exceed 64 characters")
    private String performedBy;
    
    @Size(max = 64, message = "Entity type cannot exceed 64 characters")
    private String entityType;
    
    @Size(max = 64, message = "Request ID cannot exceed 64 characters")
    private String requestId;
    
    @Size(max = 32, message = "Actor role cannot exceed 32 characters")
    private String actorRole;
    
    @Size(max = 512, message = "Payload cannot exceed 512 characters")
    private String payload;
}