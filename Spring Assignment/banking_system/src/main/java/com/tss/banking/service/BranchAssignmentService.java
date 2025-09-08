package com.tss.banking.service;

import com.tss.banking.dto.request.BranchAssignmentRequestDto;
import com.tss.banking.dto.response.BranchAssignmentResponseDto;

public interface BranchAssignmentService {
    BranchAssignmentResponseDto assignToBranch(BranchAssignmentRequestDto dto);
    BranchAssignmentResponseDto getAssignmentById(Long id);
}