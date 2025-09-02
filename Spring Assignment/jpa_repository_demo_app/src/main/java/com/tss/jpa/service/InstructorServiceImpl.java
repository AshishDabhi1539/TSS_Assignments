package com.tss.jpa.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.jpa.dto.InstructorRequestDto;
import com.tss.jpa.dto.InstructorResponseDto;
import com.tss.jpa.entity.Course;
import com.tss.jpa.entity.Instructor;
import com.tss.jpa.exception.StudentApiException;
import com.tss.jpa.repository.CourseRepository;
import com.tss.jpa.repository.InstructoreRepository;

@Service
public class InstructorServiceImpl implements InstructorService {

	@Autowired
	private InstructoreRepository instructorRepo;
	
	@Autowired
	private CourseRepository courseRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public InstructorResponseDto addNewIntructor(InstructorRequestDto dto) {

		Instructor instructor = mapper.map(dto, Instructor.class);
		Instructor dbInstructor = instructorRepo.save(instructor);

		return mapper.map(dbInstructor, InstructorResponseDto.class);
	}

	@Override
	public InstructorResponseDto assignCourse(long instructorId, long courseId) {
		Instructor instructor = instructorRepo.findById(instructorId).orElseThrow(() -> new StudentApiException("instructor not found"));
		
		List<Course> dbCourses = instructor.getCourses();
		
		Course course = courseRepo.findById(courseId).orElseThrow(() -> new StudentApiException("Course not found"));
		
		course.setInstructor(instructor);
		
		courseRepo.save(course);
		
		dbCourses.add(course);
		
		instructor.setCourses(dbCourses);
		
		Instructor updaInstructor = instructorRepo.save(instructor);
		
		return mapper.map(updaInstructor, InstructorResponseDto.class);		
	}
}
