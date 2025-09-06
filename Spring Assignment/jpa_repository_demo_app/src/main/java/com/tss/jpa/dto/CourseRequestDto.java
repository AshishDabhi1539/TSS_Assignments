package com.tss.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class CourseRequestDto {

	private String courseName;

	private int duration;

	private double fees;
}
