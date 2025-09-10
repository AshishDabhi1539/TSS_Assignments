package com.tss.banking.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.request.BankRequestDto;
import com.tss.banking.dto.request.BranchAssignmentRequestDto;
import com.tss.banking.dto.request.BranchRequestDto;
import com.tss.banking.dto.request.UserRequestDto;
import com.tss.banking.dto.response.BankResponseDto;
import com.tss.banking.dto.response.BranchAssignmentResponseDto;
import com.tss.banking.dto.response.BranchResponseDto;
import com.tss.banking.dto.response.UserResponseDto;
import com.tss.banking.entity.User;
import com.tss.banking.entity.enums.RoleType;
import com.tss.banking.entity.enums.UserStatus;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.BankService;
import com.tss.banking.service.BranchAssignmentService;
import com.tss.banking.service.BranchService;
import com.tss.banking.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/superadmin")
@Tag(name = "SuperAdmin", description = "SuperAdmin management operations")
public class SuperAdminController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	@Autowired
	private BankService bankService;

	@Autowired
	private BranchService branchService;

	@Autowired
	private BranchAssignmentService branchAssignmentService;

	@PostMapping("/initialize")
	@Operation(summary = "Initialize SuperAdmin", description = "Create SuperAdmin user if not exists - PUBLIC ENDPOINT")
	public ResponseEntity<String> initializeSuperAdmin() {
		// Check if SuperAdmin already exists
		if (userRepository.findByEmail("admin@system.com").isPresent()) {
			return ResponseEntity.ok("SuperAdmin already exists");
		}

		// Create SuperAdmin
		User superAdmin = new User();
		superAdmin.setFirstName("Super");
		superAdmin.setLastName("Admin");
		superAdmin.setEmail("admin@system.com");
		superAdmin.setPhone("1234567890");
		superAdmin.setAddress("HQ Office");
		superAdmin.setPassword(passwordEncoder.encode("admin123"));
		superAdmin.setStatus(UserStatus.VERIFIED);
		superAdmin.setRole(RoleType.SUPERADMIN);
		superAdmin.setSoftDeleted(false);

		userRepository.save(superAdmin);

		return ResponseEntity.ok("SuperAdmin created successfully");
	}

	@PostMapping("/create-admin")
	@Operation(summary = "Create Admin", description = "Create a new admin user - SUPERADMIN ONLY")
	@PreAuthorize("hasRole('SUPERADMIN')")
	public ResponseEntity<UserResponseDto> createAdmin(@Valid @RequestBody UserRequestDto dto) {
		// Set role to ADMIN for admin creation
		dto.setRole("ADMIN");

		// Register the admin user
		UserResponseDto response = userService.registerUser(dto);

		// Manually set status to VERIFIED and enabled for admin (bypassing normal flow)
		User admin = userRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new RuntimeException("Admin user not found after creation"));
		admin.setStatus(UserStatus.VERIFIED);
		userRepository.save(admin);

		return ResponseEntity.ok(response);
	}

	// Bank Management
	@PostMapping("/banks")
	@Operation(summary = "Create bank", description = "Create a new bank")
	public ResponseEntity<BankResponseDto> createBank(@Valid @RequestBody BankRequestDto dto) {
		return ResponseEntity.ok(bankService.createBank(dto));
	}

	@GetMapping("/banks")
	@Operation(summary = "Get all banks", description = "Get list of all banks")
	public ResponseEntity<List<BankResponseDto>> getAllBanks() {
		return ResponseEntity.ok(bankService.getAllBanks());
	}

	// Branch Management
	@PostMapping("/branches")
	@Operation(summary = "Create branch", description = "Create a new branch")
	public ResponseEntity<BranchResponseDto> createBranch(@Valid @RequestBody BranchRequestDto dto) {
		return ResponseEntity.ok(branchService.createBranch(dto));
	}

	@GetMapping("/branches")
	@Operation(summary = "Get all branches", description = "Get list of all branches")
	public ResponseEntity<List<BranchResponseDto>> getAllBranches() {
		return ResponseEntity.ok(branchService.getAllBranches());
	}

	@GetMapping("/banks/{bankId}/branches")
	@Operation(summary = "Get branches by bank", description = "Get all branches for a specific bank")
	public ResponseEntity<List<BranchResponseDto>> getBranchesByBank(@PathVariable Long bankId) {
		return ResponseEntity.ok(branchService.getBranchesByBank(bankId));
	}

	// Assign Admin to Branch (one admin per branch)
	@PostMapping("/branches/{branchId}/assign-admin")
	@Operation(summary = "Assign admin to branch", description = "Assign exactly one admin as MANAGER to a branch")
	public ResponseEntity<BranchAssignmentResponseDto> assignAdminToBranch(@PathVariable Long branchId,
			@Valid @RequestBody BranchAssignmentRequestDto dto) {
		dto.setBranchId(branchId);
		dto.setAssignmentType("MANAGER");
		return ResponseEntity.ok(branchAssignmentService.assignToBranch(dto));
	}
}
