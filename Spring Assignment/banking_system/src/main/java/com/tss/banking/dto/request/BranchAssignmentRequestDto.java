package com.tss.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BranchAssignmentRequestDto {
    
    @NotNull(message = "Customer ID is required")
    private Long customerId;
    
    @NotNull(message = "Branch ID is required")
    private Long branchId;
    
    @NotBlank(message = "Assignment type is required")
    @Pattern(regexp = "^(MANAGER|CUSTOMER|TRANSFER)$", 
             message = "Assignment type must be MANAGER, CUSTOMER, or TRANSFER")
    private String assignmentType;
}