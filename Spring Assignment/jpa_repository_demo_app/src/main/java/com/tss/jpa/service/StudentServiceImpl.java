package com.tss.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.jpa.entity.Student;
import com.tss.jpa.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;

	@Override
	public List<Student> readAllStudents() {

		return studentRepo.findAll();
	}

	@Override
	public Student addNewStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepo.save(student);
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