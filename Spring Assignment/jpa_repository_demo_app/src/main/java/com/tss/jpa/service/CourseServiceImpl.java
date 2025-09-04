package com.tss.jpa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.jpa.dto.CourseRequestDto;
import com.tss.jpa.dto.CourseResponseDto;
import com.tss.jpa.dto.InstructorResponseDto;
import com.tss.jpa.entity.Course;
import com.tss.jpa.entity.Instructor;
import com.tss.jpa.exception.StudentApiException;
import com.tss.jpa.repository.CourseRepository;
import com.tss.jpa.repository.InstructoreRepository;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository repo;
	
	@Autowired
	private InstructoreRepository instructorRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CourseResponseDto addNewCourse(CourseRequestDto dto) {
		Course course = mapper.map(dto, Course.class);

		Course dbCourse = repo.save(course);

		return mapper.map(dbCourse, CourseResponseDto.class);
	}

	@Override
	public InstructorResponseDto fetchInstructorByCourseId(long courseId) {
		Course course = repo.findById(courseId)
                .orElseThrow(() -> new StudentApiException("Course not found with ID: " + courseId));
        
        Instructor instructor = course.getInstructor();
        if (instructor == null) {
            throw new StudentApiException("No instructor assigned to course with ID: " + courseId);
        }
        
        return mapper.map(instructor, InstructorResponseDto.class);
	}

	@Override
	public CourseResponseDto updateCourseFees(long courseId, double newFees) {
		Course course = repo.findById(courseId)
                .orElseThrow(() -> new StudentApiException("Course not found with ID: " + courseId));
        
        if (newFees < 0) {
            throw new StudentApiException("Course fees cannot be negative");
        }
        
        course.setFees(newFees);
        Course updatedCourse = repo.save(course);
        
        return mapper.map(updatedCourse, CourseResponseDto.class);
	}

	@Override
	public List<CourseResponseDto> fetchAllCourses() {
		List<Course> courses = repo.findAll();
        return courses.stream()
                .map(course -> mapper.map(course, CourseResponseDto.class))
                .collect(Collectors.toList());
	}

	@Override
	public CourseResponseDto assignInstructor(long courseId, long instructorId) {
	    Course course = repo.findById(courseId)
	            .orElseThrow(() -> new StudentApiException("Course not found with ID: " + courseId));    
	    
	    Instructor instructor = instructorRepo.findById(instructorId)
	            .orElseThrow(() -> new StudentApiException("Instructor not found with ID: " + instructorId));
	    
	    if (course.getInstructor() != null && course.getInstructor().getInstructorId() != instructorId) {
	        throw new StudentApiException("Course is already assigned to another instructor");
	    }
	    
	    course.setInstructor(instructor);
	    repo.save(course);
	    
	    List<Course> dbCourses = instructor.getCourses();
	    if (!dbCourses.contains(course)) {
	        dbCourses.add(course);
	        instructor.setCourses(dbCourses);
	        instructorRepo.save(instructor);
	    }
	    
	    return mapper.map(course, CourseResponseDto.class);
	}
}
