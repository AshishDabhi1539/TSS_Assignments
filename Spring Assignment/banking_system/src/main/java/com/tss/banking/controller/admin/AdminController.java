package com.tss.banking.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.request.TransferActionRequestDto;
import com.tss.banking.dto.response.TransferRequestSummaryDto;
import com.tss.banking.dto.response.UserResponseDto;
import com.tss.banking.entity.User;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.InterBankTransferService;
import com.tss.banking.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin", description = "Admin Management APIs")
@PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InterBankTransferService interBankTransferService;
	
	// User Management
	@GetMapping("/users/pending")
	@Operation(summary = "Get pending users", description = "Get all users with PENDING status for approval")
	public ResponseEntity<List<UserResponseDto>> getPendingUsers() {
		return ResponseEntity.ok(userService.getPendingUsers());
	}

	@GetMapping("/users/email-verified")
	@Operation(summary = "Get email verified users", description = "Get all users with EMAIL_VERIFIED status awaiting admin approval")
	public ResponseEntity<List<UserResponseDto>> getEmailVerifiedUsers() {
		return ResponseEntity.ok(userService.getEmailVerifiedUsers());
	}

	@PutMapping("/users/{id}/approve")
	@Operation(summary = "Approve user", description = "Approve a user by changing status to VERIFIED")
	public ResponseEntity<UserResponseDto> approveUser(@PathVariable Long id, Authentication authentication) {
		String adminEmail = authentication.getName();
		return ResponseEntity.ok(userService.approveUser(id, adminEmail));
	}

	@PutMapping("/users/{id}/reject")
	@Operation(summary = "Reject user", description = "Reject a user by changing status to INACTIVE")
	public ResponseEntity<UserResponseDto> rejectUser(@PathVariable Long id) {
		return ResponseEntity.ok(userService.rejectUser(id));
	}

	/**
	 * Get all pending inter-bank transfer requests for admin review
	 * @param authentication The authentication object
	 * @return List of pending transfer requests
	 */
	@GetMapping("/inter-bank-requests/pending")
	public ResponseEntity<List<TransferRequestSummaryDto>> getPendingTransferRequests(Authentication authentication) {
		try {
			String email = authentication.getName();
			User adminUser = userRepository.findByEmail(email)
					.orElseThrow(() -> new RuntimeException("Admin user not found"));

			List<TransferRequestSummaryDto> pendingRequests = interBankTransferService.getPendingTransferRequests(adminUser);
			return ResponseEntity.ok(pendingRequests);
		} catch (RuntimeException e) {
			System.out.println("DEBUG: Get pending transfer requests error: " + e.getMessage());
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			System.out.println("DEBUG: Get pending transfer requests unexpected error: " + e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	/**
	 * Approve an inter-bank transfer request
	 * @param requestId Transfer request ID
	 * @param actionRequest Approval details
	 * @param authentication The authentication object
	 * @return Success response
	 */
	@PostMapping("/inter-bank-requests/{requestId}/approve")
	public ResponseEntity<String> approveTransferRequest(
			@PathVariable Integer requestId,
			@Valid @RequestBody TransferActionRequestDto actionRequest,
			Authentication authentication) {
		try {
			String email = authentication.getName();
			User adminUser = userRepository.findByEmail(email)
					.orElseThrow(() -> new RuntimeException("Admin user not found"));

			String result = interBankTransferService.approveTransferRequest(requestId, actionRequest, adminUser);
			return ResponseEntity.ok(result);
		} catch (RuntimeException e) {
			System.out.println("DEBUG: Approve transfer request error: " + e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			System.out.println("DEBUG: Approve transfer request unexpected error: " + e.getMessage());
			return ResponseEntity.internalServerError().body("An unexpected error occurred");
		}
	}

	/**
	 * Reject an inter-bank transfer request
	 * @param requestId Transfer request ID
	 * @param actionRequest Rejection details
	 * @param authentication The authentication object
	 * @return Success response
	 */
	@PostMapping("/inter-bank-requests/{requestId}/reject")
	public ResponseEntity<String> rejectTransferRequest(
			@PathVariable Integer requestId,
			@Valid @RequestBody TransferActionRequestDto actionRequest,
			Authentication authentication) {
		try {
			String email = authentication.getName();
			User adminUser = userRepository.findByEmail(email)
					.orElseThrow(() -> new RuntimeException("Admin user not found"));

			String result = interBankTransferService.rejectTransferRequest(requestId, actionRequest, adminUser);
			return ResponseEntity.ok(result);
		} catch (RuntimeException e) {
			System.out.println("DEBUG: Reject transfer request error: " + e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			System.out.println("DEBUG: Reject transfer request unexpected error: " + e.getMessage());
			return ResponseEntity.internalServerError().body("An unexpected error occurred");
		}
	}

	/**
	 * Process refund for rejected inter-bank transfer (called by other bank)
	 * @param refundRequest Refund request details
	 * @return Success response
	 */
	@PostMapping("/inter-bank/process-refund")
	public ResponseEntity<String> processRefund(@RequestBody Map<String, Object> refundRequest) {
		try {
			String result = interBankTransferService.processRefund(refundRequest);
			return ResponseEntity.ok(result);
		} catch (RuntimeException e) {
			System.out.println("DEBUG: Process refund error: " + e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			System.out.println("DEBUG: Process refund unexpected error: " + e.getMessage());
			return ResponseEntity.internalServerError().body("An unexpected error occurred");
		}
	}
}