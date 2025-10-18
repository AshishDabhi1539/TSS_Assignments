package com.aurionpro.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.aurionpro.app.dto.StudentRequest;
import com.aurionpro.app.dto.StudentResponse;

public interface StudentService {
	List<StudentResponse> getStudentlist();
	
	StudentResponse create(StudentRequest request);

	StudentResponse getById(Long id);

	Page<StudentResponse> list(Pageable pageable);

	StudentResponse update(Long id, StudentRequest request);

	void delete(Long id);

}
