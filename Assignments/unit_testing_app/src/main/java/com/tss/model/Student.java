package com.tss.model;

public class Student {
	private StudentService studentService;

    public Student(StudentService studentService) {
        this.studentService = studentService;
    }

    public int calculateAverage() {
        return studentService.getFinalMarks() / studentService.getNumberOfSubjects();
    }
}
