package com.tss.jpa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tss.jpa.dto.AddressRequestDto;
import com.tss.jpa.dto.StudentRequestDto;
import com.tss.jpa.dto.StudentResponseDto;
import com.tss.jpa.dto.StudentResponsePage;
import com.tss.jpa.dto.StudentWithCoursesResponseDto;
import com.tss.jpa.entity.Address;
import com.tss.jpa.entity.Course;
import com.tss.jpa.entity.Student;
import com.tss.jpa.exception.StudentApiException;
import com.tss.jpa.repository.CourseRepository;
import com.tss.jpa.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepo;
    
    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public StudentResponsePage readAllStudents(int pageSize, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Student> studentPage = studentRepo.findAll(pageable);

        List<StudentResponseDto> responses = studentPage.getContent().stream()
                .map(this::studentToStudentResponseDto)
                .collect(Collectors.toList());

        StudentResponsePage studentResponsePage = new StudentResponsePage();
        studentResponsePage.setContents(responses);
        studentResponsePage.setTotalElements((int) studentPage.getTotalElements());
        studentResponsePage.setSize(studentPage.getNumberOfElements());
        studentResponsePage.setTotalPages(studentPage.getTotalPages());
        studentResponsePage.setLastPage(studentPage.isLast());

        System.out.println(studentPage);

        return studentResponsePage;
    }

    private StudentResponseDto studentToStudentResponseDto(Student student) {
        return mapper.map(student, StudentResponseDto.class);
    }

    @Override
    public StudentResponseDto addNewStudent(StudentRequestDto studentDto) {
        // Check for duplicates (business rule)
        if (studentRepo.findByEmail(studentDto.getEmail()).isPresent()) {
            throw new StudentApiException("Email already exists: " + studentDto.getEmail());
        }
        if (studentRepo.findByRollNumber(studentDto.getRollNumber()).isPresent()) {
            throw new StudentApiException("Roll number already exists: " + studentDto.getRollNumber());
        }

        Student student = mapper.map(studentDto, Student.class);
        if (student.getAddress() == null && studentDto.getAddress() != null) {
            student.setAddress(mapper.map(studentDto.getAddress(), Address.class));
        }

        Student dbStudent = studentRepo.save(student);
        return studentToStudentResponseDto(dbStudent);
    }

    @Override
    public Student readStudentById(int studentId) {
        Optional<Student> student = studentRepo.findById(studentId);
        if (student.isPresent()) {
            return student.get();
        }
        throw new StudentApiException("Student with ID " + studentId + " not found");
    }

    @Override
    public Address getAddressByStudentId(int studentId) {
        Student student = readStudentById(studentId);
        if (student.getAddress() == null) {
            throw new StudentApiException("Address not found for student id " + studentId);
        }
        return student.getAddress();
    }

    @Override
    public Address updateAddressByStudentId(int studentId, AddressRequestDto addressRequestDto) {
        Student student = readStudentById(studentId);

        Address address = student.getAddress();
        if (address == null) {
            address = new Address();
            student.setAddress(address); // Associate new address with student
        }

        mapper.map(addressRequestDto, address); // Use mapper to update fields
        studentRepo.save(student);

        return address;
    }

	@Override
	public List<Student> findStudentByFirstment(String firstName) {
		return studentRepo.findByFirstName(firstName);
	}

	@Override
	public StudentWithCoursesResponseDto assignCourse(int studentId, long courseId) {
		Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new StudentApiException("Student not found with ID: " + studentId));
        
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new StudentApiException("Course not found with ID: " + courseId));
       
        List<Student> existingStudents = course.getStudents();
        existingStudents.add(student); 
        
        course.setStudents(existingStudents);        
        courseRepo.save(course);
        
        List<Course> dbCourses = student.getCourses();
        if (dbCourses.contains(course)) {
            throw new StudentApiException("Already Assigned This Course");
        }
        dbCourses.add(course);
        student.setCourses(dbCourses);
        studentRepo.save(student);
        return mapper.map(student, StudentWithCoursesResponseDto.class);
	}
}

	/*
	 * private Student stuedntRequestDtoToStudent(StudentRequestDto studentDto) {
	 * Student student = new Student();
	 * student.setFirstName(studentDto.getFirstName());
	 * student.setLastName(studentDto.getLastName());
	 * student.setRollNumber(studentDto.getRollNumber());
	 * student.setAge(studentDto.getAge()); student.setEmail(studentDto.getEmail());
	 * 
	 * return student; }
	 */

	/*
	 * @Override public StudentResponseDto addNewStudent(StudentRequestDto
	 * studentDto) {
	 * 
	 * //Student student = stuedntRequestDtoToStudent(studentDto);
	 * 
	 * if (studentDto.getRollNumber() <= 0) { throw new
	 * StudentApiException("Roll number must be a positive integer"); }
	 * 
	 * if (studentDto.getFirstName() == null ||
	 * !studentDto.getFirstName().matches("^[A-Za-z]{2,30}$")) { throw new
	 * StudentApiException("First name must contain only alphabets (2–30 characters)"
	 * ); }
	 * 
	 * if (studentDto.getLastName() != null && !studentDto.getLastName().isEmpty()
	 * && !studentDto.getLastName().matches("^[A-Za-z]{1,30}$")) { throw new
	 * StudentApiException("Last name must contain only alphabets (1–30 characters)"
	 * ); }
	 * 
	 * if (studentDto.getEmail() == null ||
	 * !studentDto.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) { throw
	 * new StudentApiException("Invalid email format"); }
	 * 
	 * if (studentDto.getAge() < 17 || studentDto.getAge() > 50) { throw new
	 * StudentApiException("Age must be between 17 and 50"); }
	 * 
	 * if (studentRepo.findByEmail(studentDto.getEmail()).isPresent()) { throw new
	 * StudentApiException("Email already exists: " + studentDto.getEmail()); } if
	 * (studentRepo.findByRollNumber(studentDto.getRollNumber()).isPresent()) {
	 * throw new StudentApiException("Roll number already exists: " +
	 * studentDto.getRollNumber()); }
	 * 
	 * Student student = mapper.map(studentDto, Student.class);
	 * 
	 * Student dbStudent = studentRepo.save(student);
	 * 
	 * return studentToStudentResponseDto(dbStudent); }
	 */