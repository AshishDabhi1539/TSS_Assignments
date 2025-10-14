package com.tss.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tss.security.dto.LoginDto;
import com.tss.security.dto.RegistrationDto;
import com.tss.security.dto.UserResponseDto;
import com.tss.security.entity.Role;
import com.tss.security.entity.User;
import com.tss.security.exception.UserApiException;
import com.tss.security.repository.RoleRepository;
import com.tss.security.repository.UserRepository;
import com.tss.security.security.JwtTokenProvider;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Override
	public UserResponseDto register(RegistrationDto registrationDto) {
		
		if(userRepository.existsByUsername(registrationDto.getUsername()))
			throw new UserApiException(HttpStatus.BAD_REQUEST,"User already exists");
		
		User user = new User();
		user.setUsername(registrationDto.getUsername());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		
		Role userRole = roleRepository.findByRolename(registrationDto.getRole()).
				orElseThrow(() -> new UserApiException(HttpStatus.BAD_REQUEST,"Role does not exists"));
		userRole.getUsers().add(user);
		user.setRole(userRole);
		
		user = userRepository.save(user);
		UserResponseDto dto = new UserResponseDto();
		dto.setUserId(user.getUserId());
		dto.setUsername(user.getUsername());
		
		return dto;
	}

	@Override
	public String login(LoginDto loginDto) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = tokenProvider.generateToken(authentication);
			
			return token;
		}catch (BadCredentialsException e) {
			throw new UserApiException(HttpStatus.NOT_FOUND,"Username or Password is incorrect!");
		}
	}

}
