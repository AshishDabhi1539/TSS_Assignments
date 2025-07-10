package com.tss.model;

import java.io.Serializable;

public class Student implements Serializable{
	private String name;
	private int rollNumber;
	
	public Student(String name, int rollNumber) {
		super();
		this.name = name;
		this.rollNumber = rollNumber;
	}
	
	public void display() {
		System.out.println("Name : " + name + " & Roll Number : " + rollNumber);
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", rollNumber=" + rollNumber + "]";
	}
}
