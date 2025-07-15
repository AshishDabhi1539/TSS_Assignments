package com.tss.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class StudentTest {

	private StudentService studentService;
	private Student student;

	@BeforeEach
	void init() {
		studentService = Mockito.mock(StudentService.class);
		student = new Student(studentService);
	}

	@Test
	void testCalculateAverage() {
		Mockito.when(studentService.getFinalMarks()).thenReturn(450);
		Mockito.when(studentService.getNumberOfSubjects()).thenReturn(5);

		assertEquals(90, student.calculateAverage());
	}

}
