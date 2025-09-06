package com.tss.jpa.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class InstructorWithCoursesResponseDto {
	private long instructorId;
	private String name;
	private String qualification;
	private int experience;
	private List<CourseResponseDto> courses;
}
