package com.tss.banking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tss.banking.dto.request.BranchRequestDto;
import com.tss.banking.dto.response.BranchResponseDto;
import com.tss.banking.entity.Bank;
import com.tss.banking.entity.Branch;
import com.tss.banking.entity.BranchAssignment;
import com.tss.banking.entity.User;
import com.tss.banking.entity.enums.AssignmentType;
import com.tss.banking.entity.enums.RoleType;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.BankRepository;
import com.tss.banking.repository.BranchAssignmentRepository;
import com.tss.banking.repository.BranchRepository;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.BranchService;
import com.tss.banking.service.AddressService;
import com.tss.banking.entity.enums.AddressType;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepo;

    @Autowired
    private BankRepository bankRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BranchAssignmentRepository branchAssignmentRepo;

    @Autowired
    private AddressService addressService;

    @Override
    @Transactional
    public BranchResponseDto createBranch(BranchRequestDto dto) {
        try {
            Bank bank = bankRepo.findById(dto.getBankId())
                    .orElseThrow(() -> new BankApiException("Bank not found with ID: " + dto.getBankId()));

            // Check if branch name already exists for this bank
            if (branchRepo.existsByNameAndBankId(dto.getName(), dto.getBankId())) {
                throw new BankApiException("Branch with name '" + dto.getName() + "' already exists for this bank");
            }

            // Create new branch instance instead of using mapper to avoid ID conflicts
            Branch branch = new Branch();
            branch.setName(dto.getName());
            branch.setContactNumber(dto.getContactNumber());
            branch.setBank(bank);
            
            // Generate unique codes
            String branchCode = generateUniqueBranchCode(dto.getName(), dto.getBankId());
            String ifscCode = generateUniqueIfsc(bank.getCode(), dto.getName(), dto.getBankId());
            
            branch.setCode(branchCode);
            branch.setIfsc(ifscCode);
            
            Branch savedBranch = branchRepo.save(branch);
            
            // Create address for the branch using unified address system
            createBranchAddress(savedBranch.getId(), dto);
            
            // Auto-assign admin to branch if available
            assignAdminToBranch(savedBranch);
            
            return mapBranchToResponseDto(savedBranch);
        } catch (Exception e) {
            throw new BankApiException("Failed to create branch: " + e.getMessage());
        }
    }

    @Override
    public BranchResponseDto getBranchById(Long id) {
        Branch branch = branchRepo.findById(id)
                .orElseThrow(() -> new BankApiException("Branch not found with ID: " + id));
        return mapBranchToResponseDto(branch);
    }

    @Override
    public List<BranchResponseDto> getAllBranches() {
        List<Branch> branches = branchRepo.findAll();
        return branches.stream()
                .map(this::mapBranchToResponseDto)
                .toList();
    }

    @Override
    public List<BranchResponseDto> getBranchesByBank(Long bankId) {
        List<Branch> branches = branchRepo.findByBankId(bankId);
        return branches.stream()
                .map(this::mapBranchToResponseDto)
                .toList();
    }

    private String generateUniqueBranchCode(String name, Long bankId) {
        String prefix = name != null && !name.isBlank() ? name.replaceAll("[^A-Z0-9]", "").toUpperCase() : "BR";
        if (prefix.length() > 4) prefix = prefix.substring(0, 4);
        if (prefix.length() < 2) prefix = prefix + "XX";
        
        // Get count of branches for this bank
        long count = branchRepo.countByBankId(bankId);
        String code = prefix + String.format("%03d", count + 1);
        
        // Ensure uniqueness
        int counter = 1;
        while (branchRepo.existsByCode(code)) {
            code = prefix + String.format("%03d", count + 1 + counter);
            counter++;
        }
        
        return code;
    }

    private String generateUniqueIfsc(String bankCode, String branchName, Long bankId) {
        String bankBase = bankCode != null ? bankCode.replaceAll("[^A-Z0-9]", "") : "BANK";
        if (bankBase.length() < 4) bankBase = (bankBase + "XXXX").substring(0, 4);
        
        String branchCode = branchName != null ? branchName.replaceAll("[^A-Z0-9]", "").toUpperCase() : "BR";
        if (branchCode.length() > 4) branchCode = branchCode.substring(0, 4);
        if (branchCode.length() < 2) branchCode = branchCode + "XX";
        
        // Get count of branches for this bank
        long count = branchRepo.countByBankId(bankId);
        String ifsc = bankBase + "0" + branchCode + String.format("%02d", (count + 1) % 100);
        
        // Ensure uniqueness
        int counter = 1;
        while (branchRepo.existsByIfsc(ifsc)) {
            ifsc = bankBase + "0" + branchCode + String.format("%02d", (count + 1 + counter) % 100);
            counter++;
        }
        
        return ifsc;
    }
    
    private void assignAdminToBranch(Branch branch) {
        try {
            // Find any available admin
            List<User> admins = userRepo.findByRole(RoleType.ADMIN);
            
            if (!admins.isEmpty()) {
                // Check if any admin is already assigned to this branch
                boolean hasManager = branchAssignmentRepo.existsByBranchAndAssignmentType(branch, AssignmentType.MANAGER);
                
                if (!hasManager) {
                    User admin = admins.get(0);
                    
                    // Create branch assignment
                    BranchAssignment assignment = new BranchAssignment();
                    assignment.setCustomer(admin);
                    assignment.setBranch(branch);
                    assignment.setAssignmentType(AssignmentType.MANAGER);
                    
                    branchAssignmentRepo.save(assignment);
                }
            }
        } catch (Exception e) {
            // Log error but don't fail branch creation
            System.err.println("Failed to assign admin to branch: " + e.getMessage());
        }
    }

    private void createBranchAddress(Long branchId, BranchRequestDto dto) {
        try {
            addressService.createAddressForEntity("BRANCH", branchId, AddressType.BRANCH,
                    dto.getAddressLine1(), dto.getAddressLine2(), dto.getCity(),
                    dto.getState(), dto.getPostalCode(), dto.getCountry(), null);
        } catch (Exception e) {
            System.err.println("Failed to create address for branch: " + e.getMessage());
        }
    }

    private BranchResponseDto mapBranchToResponseDto(Branch branch) {
        BranchResponseDto dto = new BranchResponseDto();
        dto.setId(branch.getId());
        dto.setName(branch.getName());
        dto.setCode(branch.getCode());
        dto.setIfsc(branch.getIfsc());
        dto.setContactNumber(branch.getContactNumber());
        dto.setBankId(branch.getBank().getId());
        
        // Get formatted address from unified address system
        try {
            dto.setAddress(addressService.getPrimaryAddress("BRANCH", branch.getId()).getFormattedAddress());
        } catch (Exception e) {
            dto.setAddress("Address not available");
        }
        
        return dto;
    }
}