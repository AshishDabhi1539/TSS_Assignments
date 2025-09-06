package com.tss.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class InstructorResponseDto {
	private long instructorId;
	private String name;
	private String qualification;
	private int experience;
}
