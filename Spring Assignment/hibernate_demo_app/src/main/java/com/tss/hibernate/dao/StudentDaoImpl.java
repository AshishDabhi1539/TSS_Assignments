package com.tss.hibernate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tss.hibernate.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	public EntityManager manager;

	@Override
	@Transactional
	public Student addNewStudent(Student student) {
		return manager.merge(student);
	}

	@Override
	public List<Student> getAllStudents() {
		TypedQuery<Student> query = manager.createQuery("select s from Student s", Student.class);
		List<Student> dbStudents = query.getResultList();
		return dbStudents;
	}

	@Override
	public Student findStudentById(int id) {
		return manager.find(Student.class, id);
	}

	@Override
	public Student getStudentByRollNumber(int rollNumber) {
		TypedQuery<Student> query = manager.createQuery("SELECT s FROM Student s WHERE s.rollNumber = :rollNumber",
				Student.class);
		query.setParameter("rollNumber", rollNumber);
		return query.getResultStream().findFirst().orElse(null);
	}

	@Override
	public Student getStudentByName(String firstName) {
		TypedQuery<Student> query = manager.createQuery("SELECT s FROM Student s WHERE s.firstName = :firstName",
				Student.class);
		query.setParameter("firstName", firstName);
		return query.getResultStream().findFirst().orElse(null);
	}

}
