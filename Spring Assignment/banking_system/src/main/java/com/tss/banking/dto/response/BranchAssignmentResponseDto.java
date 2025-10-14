package com.tss.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BranchAssignmentResponseDto {
    private Long id;
    private Long customerId;
    private Long branchId;
    private String assignmentType;
}