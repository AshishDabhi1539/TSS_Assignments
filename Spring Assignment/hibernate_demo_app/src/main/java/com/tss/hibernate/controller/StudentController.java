package com.tss.hibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tss.hibernate.entity.Student;
import com.tss.hibernate.service.StudentService;

@RestController
@RequestMapping("/studentapp")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/students")
	public Student addNewStudent(@RequestBody Student student) {
		return studentService.addNewStudent(student);
	}

	@GetMapping("/students")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/students/{id}")
	public Student getStudentById(@PathVariable int id) {
		return studentService.getStudentById(id);
	}

	@GetMapping("/roll/{rollNumber}")
	public Student getStudentByRollNumber(@PathVariable int rollNumber) {
		return studentService.getStudentByRollNumber(rollNumber);
	}

	@GetMapping("/name/get")
	public Student getStudentsByFirstName(@RequestParam("name") String firstName) {
	    return studentService.getStudentByName(firstName);
	}
}
