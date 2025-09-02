package com.tss.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class InstructorRequestDto {
	
	private String name;
	private String qualification;
	private int experience;

}
