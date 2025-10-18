package com.aurionpro.app.mapper;

import com.aurionpro.app.dto.StudentRequest;
import com.aurionpro.app.dto.StudentResponse;
import com.aurionpro.app.entity.Student;

public final class StudentMapper {
	private StudentMapper() {
	}

	public static Student toEntity(StudentRequest req) {
		return new Student(null, 
							req.getName(), 
							req.getEmail(),
							req.getAge());
	}

	public static void updateEntity(Student s, StudentRequest req) {
		s.setName(req.getName());
		s.setEmail(req.getEmail());
		s.setAge(req.getAge());
	}

	public static StudentResponse toResponse(Student s) {
		return new StudentResponse(s.getId(), s.getName(), s.getEmail(), s.getAge());
	}
}
