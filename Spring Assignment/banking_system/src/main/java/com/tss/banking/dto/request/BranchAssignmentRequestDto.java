package com.tss.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchAssignmentRequestDto {
    
    // Optional for customer operations (derived from auth), required for admin operations
    private Long customerId;
    
    @NotNull(message = "Branch ID is required")
    private Long branchId;
    
    @NotBlank(message = "Assignment type is required")
    @Pattern(regexp = "^(PRIMARY|SECONDARY|TEMPORARY)$", 
             message = "Assignment type must be PRIMARY, SECONDARY, or TEMPORARY")
    private String assignmentType;
}