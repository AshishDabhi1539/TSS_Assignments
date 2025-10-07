package com.tss.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferActionRequestDto {
    
    @NotBlank(message = "Reason is required")
    private String reason;
}
