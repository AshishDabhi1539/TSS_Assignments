package com.tss.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.jpa.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	List<Student> findByFirstName(String firstName);
}

