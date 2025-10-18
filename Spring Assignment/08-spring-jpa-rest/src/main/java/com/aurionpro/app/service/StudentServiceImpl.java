package com.aurionpro.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.app.dto.StudentRequest;
import com.aurionpro.app.dto.StudentResponse;
import com.aurionpro.app.entity.Student;
import com.aurionpro.app.exception.StudentNotFoundException;
import com.aurionpro.app.mapper.StudentMapper;
import com.aurionpro.app.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public StudentResponse create(StudentRequest request) {
        if (repo.existsByEmail(request.getEmail())) {
            throw new DataIntegrityViolationException("Email already exists");
        }
        Student saved = repo.save(StudentMapper.toEntity(request));
        return StudentMapper.toResponse(saved);
    }

    @Override
    public StudentResponse getById(Long id) {
        Student s = repo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        return StudentMapper.toResponse(s);
    }

    @Override
    public Page<StudentResponse> list(Pageable pageable) {
        return repo.findAll(pageable).map(StudentMapper::toResponse);
    }

    @Override
    public StudentResponse update(Long id, StudentRequest request) {
        Student s = repo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));

        if (!s.getEmail().equals(request.getEmail()) && repo.existsByEmail(request.getEmail())) {
            throw new DataIntegrityViolationException("Email already exists");
        }

        StudentMapper.updateEntity(s, request);
        Student saved = repo.save(s);
        return StudentMapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        Student s = repo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        repo.delete(s);
    }

	@Override
	public List<StudentResponse> getStudentlist() {
		List<StudentResponse> studentResponseList = repo.findAll().
												stream().
												map(StudentMapper::toResponse).
												collect(Collectors.toList());
		return studentResponseList;
	}
}
