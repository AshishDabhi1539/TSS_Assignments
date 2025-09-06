package com.tss.jpa.service;

import java.util.List;

import com.tss.jpa.dto.CourseResponseDto;
import com.tss.jpa.dto.InstructorRequestDto;
import com.tss.jpa.dto.InstructorResponseDto;
import com.tss.jpa.dto.InstructorWithCoursesResponseDto;
import com.tss.jpa.dto.MultipleCourseAssignmentDto;

public interface InstructorService {
	InstructorResponseDto addNewIntructor(InstructorRequestDto dto);
	
	InstructorResponseDto assignCourse(long instructorId, long courseId);
	
	List<InstructorResponseDto> fetchAllInstructors();
	
	List<CourseResponseDto> fetchCoursesByInstructorId(long instructorId);
	
	InstructorWithCoursesResponseDto removeCourse(long instructorId, long courseId);
	
	InstructorResponseDto assignMultipleCourses(long instructorId, MultipleCourseAssignmentDto dto);
}
