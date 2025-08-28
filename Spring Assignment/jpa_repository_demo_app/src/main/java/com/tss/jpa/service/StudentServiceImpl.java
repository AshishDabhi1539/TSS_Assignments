package com.tss.jpa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tss.jpa.dto.StudentRequestDto;
import com.tss.jpa.dto.StudentResponseDto;
import com.tss.jpa.dto.StudentResponsePage;
import com.tss.jpa.entity.Student;
import com.tss.jpa.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;

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
	
	private Student stuedntRequestDtoToStudent(StudentRequestDto studentDto) {
		Student student = new Student();
		student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
		student.setRollNumber(studentDto.getRollNumber());
		student.setAge(studentDto.getAge());
		student.setEmail(studentDto.getEmail());
		
		return student;
	}

	@Override
	public StudentResponseDto addNewStudent(StudentRequestDto studentDto) {
		// TODO Auto-generated method stub
		Student student = stuedntRequestDtoToStudent(studentDto);
		Student dbStudent = studentRepo.save(student);
		
		StudentResponseDto studentResp = studentToStudentResponseDto(dbStudent);
		
		return studentResp;
	}

	@Override
	public Optional<Student> readStudentById(int studentId) {
		return studentRepo.findById(studentId);
	}

	@Override
	public List<Student> findStudentByFirstment(String firstName) {
		return studentRepo.findByFirstName(firstName);
	}

	

}