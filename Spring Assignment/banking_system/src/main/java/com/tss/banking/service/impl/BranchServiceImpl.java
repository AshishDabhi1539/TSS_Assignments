package com.tss.banking.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.banking.dto.request.BranchRequestDto;
import com.tss.banking.dto.response.BranchResponseDto;
import com.tss.banking.entity.Bank;
import com.tss.banking.entity.Branch;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.BankRepository;
import com.tss.banking.repository.BranchRepository;
import com.tss.banking.service.BranchService;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepo;

    @Autowired
    private BankRepository bankRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public BranchResponseDto createBranch(BranchRequestDto dto) {
        Bank bank = bankRepo.findById(dto.getBankId())
                .orElseThrow(() -> new BankApiException("Bank not found with ID: " + dto.getBankId()));

        Branch branch = mapper.map(dto, Branch.class);
        branch.setBank(bank);
        Branch savedBranch = branchRepo.save(branch);
        return mapper.map(savedBranch, BranchResponseDto.class);
    }

    @Override
    public BranchResponseDto getBranchById(Long id) {
        Branch branch = branchRepo.findById(id)
                .orElseThrow(() -> new BankApiException("Branch not found with ID: " + id));
        return mapper.map(branch, BranchResponseDto.class);
    }
}