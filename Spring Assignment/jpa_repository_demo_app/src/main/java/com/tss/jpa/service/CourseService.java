package com.tss.jpa.service;

import java.util.List;

import com.tss.jpa.dto.CourseRequestDto;
import com.tss.jpa.dto.CourseResponseDto;
import com.tss.jpa.dto.InstructorResponseDto;

public interface CourseService {
	CourseResponseDto addNewCourse(CourseRequestDto dto);
	
	CourseResponseDto assignInstructor(long courseId, long instructorId);
	
	List<CourseResponseDto> fetchAllCourses();

	InstructorResponseDto fetchInstructorByCourseId(long courseId);
	
	CourseResponseDto updateCourseFees(long courseId, double newFees);
}
