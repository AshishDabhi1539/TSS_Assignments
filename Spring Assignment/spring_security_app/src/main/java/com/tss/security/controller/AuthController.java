package com.tss.security.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.tss.security.dto.JwtAuthResponse;
import com.tss.security.dto.LoginDto;
import com.tss.security.dto.RegistrationDto;
import com.tss.security.dto.TokenValidationResponse;
import com.tss.security.dto.UserResponseDto;
import com.tss.security.security.JwtTokenProvider;
import com.tss.security.service.AuthService;
 
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")  // Allow React frontend
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
 
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody RegistrationDto registerDto) {
        return ResponseEntity.ok(authService.register(registerDto));
    }
 
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
 
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
 
        return ResponseEntity.ok(jwtAuthResponse);
    }
    
    /**
     * Validate JWT token and return username
     * This endpoint will be called by API Gateway to validate tokens
     */
    @GetMapping("/validate")
    public ResponseEntity<TokenValidationResponse> validateToken(@RequestHeader("Authorization") String authHeader) {
        try {
            // Extract token from Bearer header
            String token = null;
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
            }
            
            if (token == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new TokenValidationResponse(false, null, "Missing or invalid Authorization header"));
            }
            
            // Validate token
            if (jwtTokenProvider.validateToken(token)) {
                String username = jwtTokenProvider.getUsername(token);
                return ResponseEntity.ok(new TokenValidationResponse(true, username, "Token is valid"));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new TokenValidationResponse(false, null, "Invalid token"));
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new TokenValidationResponse(false, null, "Token validation failed: " + e.getMessage()));
        }
    }
}