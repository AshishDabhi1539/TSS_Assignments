package com.tss.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class StudentRequestDto {
	private int rollNumber;
	private String firstName;
	private String lastName;
	private String email;
	private int age;
}
