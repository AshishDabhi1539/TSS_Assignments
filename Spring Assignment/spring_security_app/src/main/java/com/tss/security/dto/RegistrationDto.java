package com.tss.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RegistrationDto {

	private String username;
	private String password;
	private String role;
}