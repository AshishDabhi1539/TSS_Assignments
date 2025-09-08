package com.tss.security.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.tss.security.dto.JwtAuthResponse;
import com.tss.security.dto.LoginDto;
import com.tss.security.dto.RegistrationDto;
import com.tss.security.dto.UserResponseDto;
import com.tss.security.service.AuthService;
 
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")  // Allow React frontend
public class AuthController {

    @Autowired
    private AuthService authService;
 
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
}