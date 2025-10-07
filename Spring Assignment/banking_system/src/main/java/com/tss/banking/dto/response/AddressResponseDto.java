package com.tss.banking.dto.response;

import java.time.LocalDateTime;

import com.tss.banking.entity.enums.AddressType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto {
    
    private Long id;
    private String entityType;
    private Long entityId;
    private AddressType addressType;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String landmark;
    private Boolean isActive;
    private Boolean isPrimary;
    private String formattedAddress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
