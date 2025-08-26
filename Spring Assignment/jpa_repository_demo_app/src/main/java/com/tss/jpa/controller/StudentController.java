package com.tss.jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tss.jpa.entity.Student;
import com.tss.jpa.service.StudentService;

@RestController
@RequestMapping("/studentapp")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/students")
	public List<Student> readAllStudents() {
		return studentService.readAllStudents();
	}

	@PostMapping("/students")
	public Student addNewStudent(@RequestBody Student student) {
		return studentService.addNewStudent(student);
	}

	@GetMapping("/students/{studentId}")
	public Optional<Student> readStudentById(@PathVariable int studentId) {
		return studentService.readStudentById(studentId);
	}

	@GetMapping("/firstName")
	public List<Student> getEmployeesByDepartment(@RequestParam String firstName) {
		return studentService.findStudentByFirstment(firstName);
	}
}