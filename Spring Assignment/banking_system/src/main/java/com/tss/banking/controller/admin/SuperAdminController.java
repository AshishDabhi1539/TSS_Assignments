package com.tss.banking.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.request.UserRequestDto;
import com.tss.banking.dto.response.UserResponseDto;
import com.tss.banking.entity.User;
import com.tss.banking.entity.enums.UserStatus;
import com.tss.banking.entity.enums.RoleType;
import com.tss.banking.repository.UserRepository;
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
        superAdmin.setEnabled(true);
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
        admin.setEnabled(true);
        userRepository.save(admin);
        
        return ResponseEntity.ok(response);
    }
}
