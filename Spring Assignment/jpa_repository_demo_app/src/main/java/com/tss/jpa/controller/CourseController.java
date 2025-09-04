package com.tss.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tss.jpa.dto.CourseRequestDto;
import com.tss.jpa.dto.CourseResponseDto;
import com.tss.jpa.dto.InstructorResponseDto;
import com.tss.jpa.service.CourseService;

@RestController
@RequestMapping("/studentapp")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@PostMapping("/courses/add")
	public ResponseEntity<CourseResponseDto> addNewInstructor(@RequestBody CourseRequestDto dto) {
		return ResponseEntity.ok(courseService.addNewCourse(dto));
	}
	
	@GetMapping("/courses/{courseId}/instructor")
    public ResponseEntity<InstructorResponseDto> fetchInstructorByCourseId(@PathVariable long courseId) {
        return ResponseEntity.ok(courseService.fetchInstructorByCourseId(courseId));
    }
	
	@PutMapping("/courses/{courseId}/instructor")
	public ResponseEntity<CourseResponseDto> assignInstructor(@PathVariable long courseId, @RequestParam long instructorId)
	{
		return ResponseEntity.ok(courseService.assignInstructor(courseId,instructorId));
	}
	
	@GetMapping("/courses")
    public ResponseEntity<List<CourseResponseDto>> fetchAllCourses() {
        return ResponseEntity.ok(courseService.fetchAllCourses());
    }
	
	@PutMapping("/courses/{courseId}/fees")
    public ResponseEntity<CourseResponseDto> updateCourseFees(@PathVariable long courseId, @RequestParam double fees) {
        return ResponseEntity.ok(courseService.updateCourseFees(courseId, fees));
    }
}
