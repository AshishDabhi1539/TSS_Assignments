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

import com.tss.banking.dto.request.CustomerRequestDto;
import com.tss.banking.dto.request.LoginRequestDto;
import com.tss.banking.dto.response.CustomerResponseDto;
import com.tss.banking.dto.response.LoginResponseDto;
import com.tss.banking.entity.Customer;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.CustomerRepository;
import com.tss.banking.security.JwtUtil;
import com.tss.banking.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication and Registration APIs")
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/register")
    @Operation(summary = "Register a new customer", description = "Register a new customer with email and password")
    public ResponseEntity<CustomerResponseDto> registerCustomer(@Valid @RequestBody CustomerRequestDto dto) {
        return ResponseEntity.ok(customerService.registerCustomer(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "Login customer", description = "Authenticate customer and return JWT token")
    public ResponseEntity<LoginResponseDto> loginCustomer(@Valid @RequestBody LoginRequestDto loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Customer customer = customerRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BankApiException("Customer not found"));

            String token = jwtUtil.generateToken(userDetails.getUsername(), customer.getRole().name());

            return ResponseEntity.ok(new LoginResponseDto(token, customer.getEmail(), 
                customer.getRole().name(), customer.getId()));

        } catch (Exception e) {
            throw new BankApiException("Invalid email or password");
        }
    }
}