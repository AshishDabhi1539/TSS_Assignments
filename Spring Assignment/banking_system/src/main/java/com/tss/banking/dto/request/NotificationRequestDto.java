package com.tss.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestDto {
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotBlank(message = "Notification type is required")
    @Size(max = 32, message = "Notification type must not exceed 32 characters")
    @Pattern(regexp = "^(ACCOUNT_UPDATE|TRANSACTION|KYC_STATUS|SYSTEM_ALERT|PROMOTIONAL)$", 
             message = "Invalid notification type")
    private String notificationType;
    
    @NotBlank(message = "Title is required")
    @Size(max = 128, message = "Title must not exceed 128 characters")
    private String title;
    
    @NotBlank(message = "Message is required")
    @Size(max = 1024, message = "Message must not exceed 1024 characters")
    private String message;
    
    @NotBlank(message = "Channel is required")
    @Pattern(regexp = "^(EMAIL|SMS|INAPP)$", message = "Channel must be EMAIL, SMS, or INAPP")
    private String channel = "INAPP";
    
    @Size(max = 512, message = "Metadata cannot exceed 512 characters")
    private String metadata;
}
