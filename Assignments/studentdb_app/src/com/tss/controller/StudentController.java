package com.tss.controller;

import java.util.List;
import java.util.Scanner;

import com.tss.model.Student;
import com.tss.service.StudentService;

public class StudentController {
	private StudentService service;
	private static Scanner scanner = new Scanner(System.in);

	public StudentController() {
		this.service = new StudentService();
	}

	public void readAllRecords() {
		List<Student> students = service.fetchAllStudents();
		if (students.isEmpty()) {
			System.out.println("No records found.");
		} else {
			for (Student s : students) {
				System.out.println(s);
				System.out.println("---------------------------");
			}
		}
	}

	public void addNewStudent() {
		Student student = new Student();

		try {
			System.out.print("Enter Student ID: ");
			student.setStudentId(scanner.nextInt());
			scanner.nextLine();

			System.out.print("Enter Student Name: ");
			student.setStudentName(scanner.nextLine());

			System.out.print("Enter Age: ");
			student.setAge(scanner.nextInt());

			System.out.print("Enter Percentage: ");
			student.setPercentage(scanner.nextDouble());
			scanner.nextLine();

			System.out.print("Enter Roll Number: ");
			student.setRollNumber(scanner.nextLine());

			service.addStudent(student);
			System.out.println("\nStudent added successfully!\n");

		} catch (IllegalArgumentException e) {
			System.out.println("Validation Error: " + e.getMessage());
		}
	}

	public void readStudentById() {
		System.out.print("Enter Student ID: ");
		int id = scanner.nextInt();
		Student student = service.readStudentById(id);
		if (student != null) {
			System.out.println(student);
		} else {
			System.out.println("Student not found!");
		}
	}

	public void updatePercentageById() {
		System.out.print("Enter Student ID: ");
		int id = scanner.nextInt();

		System.out.print("Enter New Percentage: ");
		double percentage = scanner.nextDouble();

		service.updateStudentPercentageById(id, percentage);
	}

	public void deleteStudentById() {
		System.out.print("Enter Student ID: ");
		int id = scanner.nextInt();
		service.deleteStudentById(id);
	}
}
