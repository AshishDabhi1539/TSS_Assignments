package com.tss.policy.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class InsurancePolicyResponsePage {
    private List<InsurancePolicyResponseDto> contents;
    private int totalElements;
    private int size;
    private boolean isLastPage;
    private int totalPages;
}