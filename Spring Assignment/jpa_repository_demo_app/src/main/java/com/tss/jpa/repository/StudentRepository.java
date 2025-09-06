package com.tss.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.jpa.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	Optional<Student> findByEmail(String email);
    Optional<Student> findByRollNumber(int rollNumber);
	List<Student> findByFirstName(String firstName);
}

