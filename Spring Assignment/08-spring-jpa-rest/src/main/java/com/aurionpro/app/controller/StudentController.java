package com.aurionpro.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.aurionpro.app.dto.StudentRequest;
import com.aurionpro.app.dto.StudentResponse;
import com.aurionpro.app.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(
		  origins = "http://localhost:4200",
		  allowedHeaders = {"Content-Type", "Authorization"},
		  methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
		)
public class StudentController {

	private final StudentService service;

	public StudentController(StudentService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentRequest request,
			UriComponentsBuilder uriBuilder) {
		StudentResponse created = service.create(request);
		return ResponseEntity.created(uriBuilder.path("/api/students/{id}").buildAndExpand(created.getId()).toUri())
				.body(created);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentResponse> getById(@PathVariable Long id) {
		return ResponseEntity.ok(service.getById(id));
	}

//	@GetMapping
//	public ResponseEntity<Page<StudentResponse>> list(Pageable pageable) {
//		return ResponseEntity.ok(service.list(pageable));
//	}
	@GetMapping
	public ResponseEntity<List<StudentResponse>> list() {
		return ResponseEntity.ok(service.getStudentlist());
	} 

	@PutMapping("/{id}")
	public ResponseEntity<StudentResponse> update(@PathVariable Long id, @Valid @RequestBody StudentRequest request) {
		return ResponseEntity.ok(service.update(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
