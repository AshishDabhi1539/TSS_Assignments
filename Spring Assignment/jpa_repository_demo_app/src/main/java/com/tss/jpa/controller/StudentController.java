package com.tss.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tss.jpa.dto.AddressRequestDto;
import com.tss.jpa.dto.StudentRequestDto;
import com.tss.jpa.dto.StudentResponseDto;
import com.tss.jpa.dto.StudentResponsePage;
import com.tss.jpa.dto.StudentWithCoursesResponseDto;
import com.tss.jpa.entity.Address;
import com.tss.jpa.entity.Student;
import com.tss.jpa.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/studentapp")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<StudentResponsePage> readAllStudents(@RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "0") int pageNo) {
        StudentResponsePage students = studentService.readAllStudents(pageSize, pageNo);
        return ResponseEntity.ok(students);
    }

    @PostMapping("/students")
    public ResponseEntity<StudentResponseDto> addNewStudent(@Valid @RequestBody StudentRequestDto student) {
        return ResponseEntity.ok().header("author", "Ashish").body(studentService.addNewStudent(student));
    }

    @GetMapping("/students/{studentId}/address")
    public ResponseEntity<Address> getAddressByStudentId(@PathVariable int studentId) {
        return ResponseEntity.ok(studentService.getAddressByStudentId(studentId));
    }

    @PutMapping("/students/{studentId}/address")
    public ResponseEntity<Address> updateAddressByStudentId(@PathVariable int studentId,
            @Valid @RequestBody AddressRequestDto addressRequestDto) {
        Address updatedAddress = studentService.updateAddressByStudentId(studentId, addressRequestDto);
        return ResponseEntity.ok(updatedAddress);
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> readStudentById(@PathVariable int studentId) {
        Student student = studentService.readStudentById(studentId);
        return ResponseEntity.ok(student);
    }
    
    @PutMapping("/students/{studentId}/courses/{courseId}")
    public ResponseEntity<StudentWithCoursesResponseDto> assignCourse(@PathVariable int studentId, @PathVariable long courseId) {
        return ResponseEntity.ok(studentService.assignCourse(studentId, courseId));
    }
}

	/*
	 * @GetMapping("/students") public ResponseEntity<List<StudentResponseDto>>
	 * readAllStudents() { List<StudentResponseDto> students =
	 * studentService.readAllStudents(); // if (students.isEmpty()) // return
	 * ResponseEntity.noContent().build();
	 * 
	 * return ResponseEntity.ok(students); }
	 */

	/*
	 * @GetMapping("/students/{studentId}") public Optional<Student>
	 * readStudentById(@PathVariable int studentId) { return
	 * studentService.readStudentById(studentId); }
	 */

	/*
	 * @GetMapping("/students/{studentId}") public ResponseEntity<Student>
	 * readStudentById(@PathVariable int studentId) { Student student =
	 * studentService.readStudentById(studentId); return
	 * student.map(ResponseEntity::ok).orElseGet(() ->
	 * ResponseEntity.notFound().build()); }
	 */

	/*
	 * @GetMapping("/firstName") public List<Student>
	 * getEmployeesByDepartment(@RequestParam String firstName) { return
	 * studentService.findStudentByFirstment(firstName); }
	 */

	/*
	 * @GetMapping("/students") public ResponseEntity<List<Student>>
	 * getStudents(@RequestParam(required = false) String firstName) { List<Student>
	 * students;
	 * 
	 * if (firstName == null || firstName.isEmpty()) { students =
	 * studentService.readAllStudents(); } else { students =
	 * studentService.findStudentByFirstment(firstName); }
	 * 
	 * if (students.isEmpty()) { return ResponseEntity.noContent().build(); // 204
	 * No Content } return ResponseEntity.ok(students); // 200 OK }
	 */