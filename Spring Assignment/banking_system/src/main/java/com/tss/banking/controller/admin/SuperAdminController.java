package com.tss.banking.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.entity.User;
import com.tss.banking.entity.enums.UserStatus;
import com.tss.banking.entity.enums.RoleType;
import com.tss.banking.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/superadmin")
@Tag(name = "SuperAdmin", description = "SuperAdmin management operations")
@PreAuthorize("hasRole('SUPERADMIN')")
public class SuperAdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/initialize")
    @Operation(summary = "Initialize SuperAdmin", description = "Create SuperAdmin user if not exists")
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
        
        userRepository.save(superAdmin);
        
        return ResponseEntity.ok("SuperAdmin created successfully");
    }
}
