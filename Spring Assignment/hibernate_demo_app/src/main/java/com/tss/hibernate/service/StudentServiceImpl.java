package com.tss.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.hibernate.dao.StudentDao;
import com.tss.hibernate.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Override
	public Student addNewStudent(Student student) {
		return studentDao.addNewStudent(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}

	@Override
	public Student getStudentById(int id) {
		return studentDao.findStudentById(id);
	}

	@Override
	public Student getStudentByRollNumber(int rollNumber) {
		return studentDao.getStudentByRollNumber(rollNumber);
	}

	@Override
	public Student getStudentByName(String firstName) {
		return studentDao.getStudentByName(firstName);
	}

}
