package com.tss.jpa.service;

import com.tss.jpa.dto.InstructorRequestDto;
import com.tss.jpa.dto.InstructorResponseDto;

public interface InstructorService {
	InstructorResponseDto addNewIntructor(InstructorRequestDto dto);
	
	InstructorResponseDto assignCourse(long instructorId, long courseId);
}
