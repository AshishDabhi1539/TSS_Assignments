package com.tss.jpa.service;

import java.util.List;
import java.util.Optional;

import com.tss.jpa.entity.Student;

public interface StudentService {

	List<Student> readAllStudents();

	Student addNewStudent(Student student);

	Optional<Student> readStudentById(int studentId);

	List<Student> findStudentByFirstment(String firstName);
}