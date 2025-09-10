package com.tss.banking.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.response.UserResponseDto;
import com.tss.banking.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin", description = "Admin Management APIs")
@PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
public class AdminController {

	@Autowired
	private UserService userService;

	// User Management
	@GetMapping("/users/pending")
	@Operation(summary = "Get pending users", description = "Get all users with PENDING status for approval")
	public ResponseEntity<List<UserResponseDto>> getPendingUsers() {
		return ResponseEntity.ok(userService.getPendingUsers());
	}

	@PutMapping("/users/{id}/approve")
	@Operation(summary = "Approve user", description = "Approve a user by changing status to VERIFIED")
	public ResponseEntity<UserResponseDto> approveUser(@PathVariable Long id) {
		return ResponseEntity.ok(userService.approveUser(id));
	}

	@PutMapping("/users/{id}/reject")
	@Operation(summary = "Reject user", description = "Reject a user by changing status to INACTIVE")
	public ResponseEntity<UserResponseDto> rejectUser(@PathVariable Long id) {
		return ResponseEntity.ok(userService.rejectUser(id));
	}
}