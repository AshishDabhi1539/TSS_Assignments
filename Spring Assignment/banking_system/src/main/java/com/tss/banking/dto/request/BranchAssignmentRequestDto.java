package com.tss.banking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BranchAssignmentRequestDto {
    private Long customerId;
    private Long branchId;
    private String assignmentType; // e.g., MANAGER, CUSTOMER
}