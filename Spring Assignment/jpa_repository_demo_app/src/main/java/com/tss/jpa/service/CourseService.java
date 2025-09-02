package com.tss.jpa.service;

import com.tss.jpa.dto.CourseRequestDto;
import com.tss.jpa.dto.CourseResponseDto;

public interface CourseService {
	CourseResponseDto addNewCourse(CourseRequestDto dto);
}
