package com.tss.banking.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.banking.dto.request.BranchAssignmentRequestDto;
import com.tss.banking.dto.response.BranchAssignmentResponseDto;
import com.tss.banking.entity.Branch;
import com.tss.banking.entity.BranchAssignment;
import com.tss.banking.entity.User;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.BranchAssignmentRepository;
import com.tss.banking.repository.BranchRepository;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.BranchAssignmentService;

@Service
public class BranchAssignmentServiceImpl implements BranchAssignmentService {

    @Autowired
    private BranchAssignmentRepository branchAssignmentRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BranchRepository branchRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public BranchAssignmentResponseDto assignToBranch(BranchAssignmentRequestDto dto) {
        User user = userRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new BankApiException("User not found with ID: " + dto.getCustomerId()));
        Branch branch = branchRepo.findById(dto.getBranchId())
                .orElseThrow(() -> new BankApiException("Branch not found with ID: " + dto.getBranchId()));

        BranchAssignment assignment = mapper.map(dto, BranchAssignment.class);
        assignment.setCustomer(user);
        assignment.setBranch(branch);
        BranchAssignment savedAssignment = branchAssignmentRepo.save(assignment);
        return mapper.map(savedAssignment, BranchAssignmentResponseDto.class);
    }

    @Override
    public BranchAssignmentResponseDto getAssignmentById(Long id) {
        BranchAssignment assignment = branchAssignmentRepo.findById(id)
                .orElseThrow(() -> new BankApiException("Branch assignment not found with ID: " + id));
        return mapper.map(assignment, BranchAssignmentResponseDto.class);
    }
}