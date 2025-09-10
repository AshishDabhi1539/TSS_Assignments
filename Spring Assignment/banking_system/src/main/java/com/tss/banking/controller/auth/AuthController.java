package com.tss.banking.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.request.UserRequestDto;
import com.tss.banking.dto.request.LoginRequestDto;
import com.tss.banking.dto.response.UserResponseDto;
import com.tss.banking.dto.response.LoginResponseDto;
import com.tss.banking.entity.User;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.security.JwtUtil;
import com.tss.banking.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication and Registration APIs")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Register a new user with email and password")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(userService.registerUser(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Authenticate user (Customer/Admin/SuperAdmin) and return JWT token")
    public ResponseEntity<LoginResponseDto> loginUser(@Valid @RequestBody LoginRequestDto loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BankApiException("User not found"));

            String token = jwtUtil.generateToken(userDetails.getUsername(), user.getRole().name());

            return ResponseEntity.ok(new LoginResponseDto(token, user.getEmail(), 
                user.getRole().name(), user.getId()));

        } catch (Exception e) {
            throw new BankApiException("Invalid email or password");
        }
    }
}