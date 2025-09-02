package com.tss.jpa.service;

import java.util.List;

import com.tss.jpa.dto.AddressRequestDto;
import com.tss.jpa.dto.StudentRequestDto;
import com.tss.jpa.dto.StudentResponseDto;
import com.tss.jpa.dto.StudentResponsePage;
import com.tss.jpa.entity.Address;
import com.tss.jpa.entity.Student;

public interface StudentService {

	StudentResponsePage readAllStudents(int pageSize, int pageNo);

	StudentResponseDto addNewStudent(StudentRequestDto student);

	Student readStudentById(int studentId);

	List<Student> findStudentByFirstment(String firstName);

	Address getAddressByStudentId(int studentId);
	
	Address updateAddressByStudentId(int studentId, AddressRequestDto addressRequestDto);
}