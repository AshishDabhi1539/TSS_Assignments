package com.tss.banking.service;

import java.util.List;

import com.tss.banking.dto.request.BranchRequestDto;
import com.tss.banking.dto.response.BranchResponseDto;

public interface BranchService {
    BranchResponseDto createBranch(BranchRequestDto dto);
    BranchResponseDto getBranchById(Long id);
    List<BranchResponseDto> getAllBranches();
    List<BranchResponseDto> getBranchesByBank(Long bankId);
}