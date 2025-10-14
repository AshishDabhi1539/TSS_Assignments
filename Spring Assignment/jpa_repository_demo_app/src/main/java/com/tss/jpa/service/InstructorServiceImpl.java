package com.tss.jpa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.jpa.dto.CourseResponseDto;
import com.tss.jpa.dto.InstructorRequestDto;
import com.tss.jpa.dto.InstructorResponseDto;
import com.tss.jpa.dto.InstructorWithCoursesResponseDto;
import com.tss.jpa.dto.MultipleCourseAssignmentDto;
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

	/*@Override
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
	}*/
	
	@Override
    public InstructorResponseDto assignCourse(long instructorId, long courseId) {
        Instructor instructor = instructorRepo.findById(instructorId)
                .orElseThrow(() -> new StudentApiException("Instructor not found with ID: " + instructorId));
        
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new StudentApiException("Course not found with ID: " + courseId));
        
        if (course.getInstructor() != null && course.getInstructor().getInstructorId() != instructorId) {
            throw new StudentApiException("Course is already assigned to another instructor");
        }
        
        course.setInstructor(instructor);
        courseRepo.save(course);
        
        List<Course> dbCourses = instructor.getCourses();
        if (!dbCourses.contains(course)) {
            dbCourses.add(course);
            instructor.setCourses(dbCourses);
            instructorRepo.save(instructor);
        }
        
        return mapper.map(instructor, InstructorResponseDto.class);
    }

	@Override
	public List<InstructorResponseDto> fetchAllInstructors() {
		List<Instructor> instructors = instructorRepo.findAll();
        return instructors.stream()
                .map(instructor -> mapper.map(instructor, InstructorResponseDto.class))
                .collect(Collectors.toList());
	}

	@Override
    public List<CourseResponseDto> fetchCoursesByInstructorId(long instructorId) {
        Instructor instructor = instructorRepo.findById(instructorId)
                .orElseThrow(() -> new StudentApiException("Instructor not found with ID: " + instructorId));
        
        List<Course> courses = instructor.getCourses();
        if (courses == null || courses.isEmpty()) {
            throw new StudentApiException("No courses assigned to instructor with ID: " + instructorId);
        }
        
        return courses.stream()
                .map(course -> mapper.map(course, CourseResponseDto.class))
                .collect(Collectors.toList());
    }

	@Override
	public InstructorWithCoursesResponseDto removeCourse(long instructorId, long courseId) {
		Instructor instructor = instructorRepo.findById(instructorId)
                .orElseThrow(() -> new StudentApiException("Instructor not found with ID: " + instructorId));
        
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new StudentApiException("Course not found with ID: " + courseId));
        
        if (course.getInstructor() == null || course.getInstructor().getInstructorId() != instructorId) {
            throw new StudentApiException("Course is not assigned to this instructor");
        }
        
        course.setInstructor(null);
        courseRepo.save(course);
        
        List<Course> dbCourses = instructor.getCourses();
        dbCourses.remove(course);
        instructor.setCourses(dbCourses);
        instructorRepo.save(instructor);        
        
        return mapper.map(instructor, InstructorWithCoursesResponseDto.class);
	}

	@Override
	public InstructorResponseDto assignMultipleCourses(long instructorId, MultipleCourseAssignmentDto dto) {
		Instructor instructor = instructorRepo.findById(instructorId)
                .orElseThrow(() -> new StudentApiException("Instructor not found with ID: " + instructorId));
        
        List<Course> dbCourses = instructor.getCourses();
        
        for (Long courseId : dto.getCourseIds()) {
            Course course = courseRepo.findById(courseId)
                    .orElseThrow(() -> new StudentApiException("Course not found with ID: " + courseId));
            
            if (course.getInstructor() != null && course.getInstructor().getInstructorId() != instructorId) {
                throw new StudentApiException("Course with ID " + courseId + " is already assigned to another instructor");
            }
            
            course.setInstructor(instructor);
            courseRepo.save(course);
            
            if (!dbCourses.contains(course)) {
                dbCourses.add(course);
            }
        }
        
        instructor.setCourses(dbCourses);
        instructorRepo.save(instructor);
        
        return mapper.map(instructor, InstructorResponseDto.class);
	}
}
