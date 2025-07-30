package com.tss.service;

import java.util.List;

import com.tss.dao.StudentDao;
import com.tss.model.Student;

public class StudentService {
	private StudentDao dao;

    public StudentService() {
        this.dao = new StudentDao();
    }

    public List<Student> fetchAllStudents() {
        return dao.readAllStudents();
    }

    public void addStudent(Student student) {
        dao.addNewStudent(student);
    }

    public Student readStudentById(int id) {
        return dao.readStudentById(id);
    }

    public void updateStudentPercentageById(int id, double percentage) {
        dao.updateStudentPercentageById(id, percentage);
    }

    public void deleteStudentById(int id) {
        dao.deleteStudentById(id);
    }
}
