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

import com.tss.jpa.dto.StudentRequestDto;
import com.tss.jpa.dto.StudentResponseDto;
import com.tss.jpa.dto.StudentResponsePage;
import com.tss.jpa.entity.Student;
import com.tss.jpa.exception.StudentApiException;
import com.tss.jpa.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public StudentResponsePage readAllStudents(int pageSize, int pageNo) {
	    Pageable pageable = PageRequest.of(pageNo, pageSize);
	    Page<Student> studentPage = studentRepo.findAll(pageable);

	    List<StudentResponseDto> responses = studentPage.getContent()
	            .stream()
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
	
	private StudentResponseDto studentToStudentResponseDto(Student student){
		StudentResponseDto dto = new StudentResponseDto();
		dto.setFirstName(student.getFirstName());
		dto.setLastName(student.getLastName());
		return dto;
	}
	
	/*private Student stuedntRequestDtoToStudent(StudentRequestDto studentDto) {
		Student student = new Student();
		student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
		student.setRollNumber(studentDto.getRollNumber());
		student.setAge(studentDto.getAge());
		student.setEmail(studentDto.getEmail());
		
		return student;
	}*/

	/*@Override
	public StudentResponseDto addNewStudent(StudentRequestDto studentDto) {

		//Student student = stuedntRequestDtoToStudent(studentDto);
		
	    if (studentDto.getRollNumber() <= 0) {
	        throw new StudentApiException("Roll number must be a positive integer");
	    }

	    if (studentDto.getFirstName() == null || 
	        !studentDto.getFirstName().matches("^[A-Za-z]{2,30}$")) {
	        throw new StudentApiException("First name must contain only alphabets (2–30 characters)");
	    }

	    if (studentDto.getLastName() != null && 
	        !studentDto.getLastName().isEmpty() &&
	        !studentDto.getLastName().matches("^[A-Za-z]{1,30}$")) {
	        throw new StudentApiException("Last name must contain only alphabets (1–30 characters)");
	    }

	    if (studentDto.getEmail() == null || 
	        !studentDto.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
	        throw new StudentApiException("Invalid email format");
	    }

	    if (studentDto.getAge() < 17 || studentDto.getAge() > 50) {
	        throw new StudentApiException("Age must be between 17 and 50");
	    }

	    if (studentRepo.findByEmail(studentDto.getEmail()).isPresent()) {
	        throw new StudentApiException("Email already exists: " + studentDto.getEmail());
	    }
	    if (studentRepo.findByRollNumber(studentDto.getRollNumber()).isPresent()) {
	        throw new StudentApiException("Roll number already exists: " + studentDto.getRollNumber());
	    }

	    Student student = mapper.map(studentDto, Student.class);

	    Student dbStudent = studentRepo.save(student);

	    return studentToStudentResponseDto(dbStudent);
	}*/
	
	@Override
	public StudentResponseDto addNewStudent(StudentRequestDto studentDto) {
	    // check for duplicates only (business rule, not field validation)
	    if (studentRepo.findByEmail(studentDto.getEmail()).isPresent()) {
	        throw new StudentApiException("Email already exists: " + studentDto.getEmail());
	    }
	    if (studentRepo.findByRollNumber(studentDto.getRollNumber()).isPresent()) {
	        throw new StudentApiException("Roll number already exists: " + studentDto.getRollNumber());
	    }
	    
	    if (studentDto.getRollNumber() <= 0) {
	        throw new StudentApiException("Roll number must be a positive integer");
	    }

	    if (studentDto.getFirstName() == null || 
	        !studentDto.getFirstName().matches("^[A-Za-z]{2,30}$")) {
	        throw new StudentApiException("First name must contain only alphabets (2–30 characters)");
	    }

	    if (studentDto.getLastName() != null && 
	        !studentDto.getLastName().isEmpty() &&
	        !studentDto.getLastName().matches("^[A-Za-z]{1,30}$")) {
	        throw new StudentApiException("Last name must contain only alphabets (1–30 characters)");
	    }

	    if (studentDto.getEmail() == null || 
	        !studentDto.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
	        throw new StudentApiException("Invalid email format");
	    }

	    if (studentDto.getAge() < 17 || studentDto.getAge() > 50) {
	        throw new StudentApiException("Age must be between 17 and 50");
	    }

	    if (studentRepo.findByEmail(studentDto.getEmail()).isPresent()) {
	        throw new StudentApiException("Email already exists: " + studentDto.getEmail());
	    }
	    if (studentRepo.findByRollNumber(studentDto.getRollNumber()).isPresent()) {
	        throw new StudentApiException("Roll number already exists: " + studentDto.getRollNumber());
	    }

	    Student student = mapper.map(studentDto, Student.class);
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
	public List<Student> findStudentByFirstment(String firstName) {
		return studentRepo.findByFirstName(firstName);
	}

	

}