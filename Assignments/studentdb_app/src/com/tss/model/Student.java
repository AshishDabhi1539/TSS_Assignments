package com.tss.model;

public class Student {
	private int studentId;
	private String studentName;
	private int age;
	private double percentage;
	private String rollNumber;

	public Student() {
		super();
	}

	public Student(int studentId, String studentName, int age, double percentage, String rollNumber) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.age = age;
		this.percentage = percentage;
		this.rollNumber = rollNumber;
	}

	public int getStudentId() {
		return studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentId(int studentId) {
		if (studentId <= 0) {
			throw new IllegalArgumentException("Student ID must be a positive integer.");
		}
		this.studentId = studentId;
	}

	public void setStudentName(String studentName) {
		if (studentName == null || studentName.trim().isEmpty()) {
			throw new IllegalArgumentException("Student name cannot be empty.");
		}
		this.studentName = studentName;
	}

	public void setAge(int age) {
		if (age < 5 || age > 100) {
			throw new IllegalArgumentException("Age must be between 5 and 100.");
		}
		this.age = age;
	}

	public void setPercentage(double percentage) {
		if (percentage < 0 || percentage > 100) {
			throw new IllegalArgumentException("Percentage must be between 0 and 100.");
		}
		this.percentage = percentage;
	}

	public void setRollNumber(String rollNumber) {
		if (rollNumber == null || rollNumber.trim().isEmpty()) {
			throw new IllegalArgumentException("Roll number cannot be empty.");
		}
		this.rollNumber = rollNumber;
	}

	public int getAge() {
		return age;
	}

	public double getPercentage() {
		return percentage;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	@Override
	public String toString() {
		return "Student [ID=" + studentId + ", Name=" + studentName + ", Age=" + age + ", Percentage=" + percentage
				+ ", Roll No=" + rollNumber + "]";
	}
}
