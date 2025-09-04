package com.tss.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tss.jpa.dto.CourseResponseDto;
import com.tss.jpa.dto.InstructorRequestDto;
import com.tss.jpa.dto.InstructorResponseDto;
import com.tss.jpa.dto.InstructorWithCoursesResponseDto;
import com.tss.jpa.dto.MultipleCourseAssignmentDto;
import com.tss.jpa.service.InstructorService;

@RestController
@RequestMapping("/studentapp")
public class InstructorController {
	
	@Autowired
	private InstructorService instructorService;
	
	@PostMapping("/instructors/add")
	public ResponseEntity<InstructorResponseDto> addNewInstructor(@RequestBody InstructorRequestDto dto)
	{
		return ResponseEntity.ok(instructorService.addNewIntructor(dto));
	}
	
	@PutMapping("/instructors/{instructorId}/courses")
	public ResponseEntity<InstructorResponseDto> assignCourse(@PathVariable long instructorId, @RequestParam long courseId)
	{
		return ResponseEntity.ok(instructorService.assignCourse(instructorId,courseId));
	}
	
	@GetMapping("/instructors")
    public ResponseEntity<List<InstructorResponseDto>> fetchAllInstructors() {
        return ResponseEntity.ok(instructorService.fetchAllInstructors());
    }
	
	@GetMapping("/instructors/{instructorId}/courses")
    public ResponseEntity<List<CourseResponseDto>> fetchCoursesByInstructorId(@PathVariable long instructorId) {
        return ResponseEntity.ok(instructorService.fetchCoursesByInstructorId(instructorId));
    }
	
	@DeleteMapping("/instructors/{instructorId}/courses/{courseId}")
    public ResponseEntity<InstructorWithCoursesResponseDto> removeCourse(@PathVariable long instructorId, @PathVariable long courseId) {
        return ResponseEntity.ok(instructorService.removeCourse(instructorId, courseId));
    }
	
	@PutMapping("/instructors/{instructorId}/multiple-courses")
    public ResponseEntity<InstructorResponseDto> assignMultipleCourses(@PathVariable long instructorId, @RequestBody MultipleCourseAssignmentDto dto) {
        return ResponseEntity.ok(instructorService.assignMultipleCourses(instructorId, dto));
    }
}
