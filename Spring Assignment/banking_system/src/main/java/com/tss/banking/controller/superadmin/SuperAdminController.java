package com.tss.banking.controller.superadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.request.BankRequestDto;
import com.tss.banking.dto.request.BranchRequestDto;
import com.tss.banking.dto.response.BankResponseDto;
import com.tss.banking.dto.response.BranchResponseDto;
import com.tss.banking.service.BankService;
import com.tss.banking.service.BranchService;

@RestController
@RequestMapping("/superadmin")
public class SuperAdminController {

    @Autowired
    private BankService bankService;

    @Autowired
    private BranchService branchService;

    @PostMapping("/banks")
    public ResponseEntity<BankResponseDto> createBank(@RequestBody BankRequestDto dto) {
        return ResponseEntity.ok(bankService.createBank(dto));
    }

    @PostMapping("/branches")
    public ResponseEntity<BranchResponseDto> createBranch(@RequestBody BranchRequestDto dto) {
        return ResponseEntity.ok(branchService.createBranch(dto));
    }
}