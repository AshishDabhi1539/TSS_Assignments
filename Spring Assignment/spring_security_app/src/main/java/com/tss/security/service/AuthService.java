package com.tss.security.service;

import com.tss.security.dto.LoginDto;
import com.tss.security.dto.RegistrationDto;
import com.tss.security.dto.UserResponseDto;

public interface AuthService {
	UserResponseDto register(RegistrationDto registration);
	String login(LoginDto loginDto);
}
