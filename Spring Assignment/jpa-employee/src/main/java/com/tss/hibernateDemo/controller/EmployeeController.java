package com.tss.hibernateDemo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.hibernateDemo.dto.EmployeeRequest;
import com.tss.hibernateDemo.dto.EmployeeResponse;
import com.tss.hibernateDemo.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	@PostMapping("/create")
	public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody EmployeeRequest request) {
		EmployeeResponse response = employeeService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).header("author", "Ashish").body(response);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<EmployeeResponse> getById(@PathVariable int id) {
		EmployeeResponse response = employeeService.getById(id);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/allEmployee")
	public ResponseEntity<List<EmployeeResponse>> getAll() {
		List<EmployeeResponse> responses = employeeService.getAll();
		return ResponseEntity.ok(responses);
	}

	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<EmployeeResponse> update(@PathVariable int id, @Valid @RequestBody EmployeeRequest request) {
		EmployeeResponse response = employeeService.update(id, request);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		employeeService.delete(id);
		return ResponseEntity.noContent().build();
	}
}