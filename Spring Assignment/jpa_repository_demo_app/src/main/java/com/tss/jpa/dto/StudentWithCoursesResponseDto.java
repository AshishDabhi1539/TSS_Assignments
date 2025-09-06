package com.tss.jpa.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentWithCoursesResponseDto {
	private String firstName;
	private String lastName;
	private List<CourseResponseDto> courses;
}
