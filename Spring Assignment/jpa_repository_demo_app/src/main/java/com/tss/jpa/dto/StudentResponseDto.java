package com.tss.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class StudentResponseDto {
	private String firstName;
	private String lastName;
}
