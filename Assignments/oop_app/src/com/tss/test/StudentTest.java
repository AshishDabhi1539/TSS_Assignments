package com.tss.test;

import java.util.Scanner;

import com.tss.model.Student;

public class StudentTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		Student student1 = new Student();
		Student student2 = new Student();

		student1Details(student1,scanner);

		student2Details(student2,scanner);
		
		student1.display();
		student2.display();
	}

	private static void student1Details(Student student1,Scanner scanner) {
		System.out.println("Enter student1 details : ");
		
		System.out.print("Enter Roll Number : ");
		int rollNumber = scanner.nextInt();

		scanner.nextLine();
		System.out.print("Enter Name : ");
		String name = scanner.nextLine();

		System.out.print("Enter Age : ");
		int age = scanner.nextInt();

		System.out.print("Enter Subject1 Marks : ");
		int sub1 = scanner.nextInt();

		System.out.print("Enter Subject2 Marks : ");
		int sub2 = scanner.nextInt();

		System.out.print("Enter Subject3 Marks : ");
		int sub3 = scanner.nextInt();

		student1.setRollNumber(rollNumber);
		student1.setName(name);
		student1.setAge(age);
		student1.setMarks(sub1, sub2, sub3);
	}
	

	private static void student2Details(Student student2,Scanner scanner) {
		System.out.println("\nEnter student2 details : ");

		System.out.print("Enter Roll Number : ");
		int rollNumber = scanner.nextInt();

		scanner.nextLine();
		System.out.print("Enter Name : ");
		String name = scanner.nextLine();

		System.out.print("Enter Age : ");
		int age = scanner.nextInt();

		System.out.print("Enter Subject1 Marks : ");
		int sub1 = scanner.nextInt();

		System.out.print("Enter Subject2 Marks : ");
		int sub2 = scanner.nextInt();

		System.out.print("Enter Subject3 Marks : ");
		int sub3 = scanner.nextInt();

		student2.setRollNumber(rollNumber);
		student2.setName(name);
		student2.setAge(age);
		student2.setMarks(sub1, sub2, sub3);
	}

}
