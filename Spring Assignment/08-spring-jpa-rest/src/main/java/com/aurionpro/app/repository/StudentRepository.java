package com.aurionpro.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.app.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	boolean existsByEmail(String email);

	Optional<Student> findByEmail(String email);
}
