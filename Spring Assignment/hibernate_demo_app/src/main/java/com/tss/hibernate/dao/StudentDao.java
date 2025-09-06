package com.tss.hibernate.dao;

import java.util.List;

import com.tss.hibernate.entity.Student;

public interface StudentDao {

	Student addNewStudent(Student student);

	List<Student> getAllStudents();

	Student findStudentById(int id);

	Student getStudentByRollNumber(int rollNumber);

	Student getStudentByName(String firstName);
}
