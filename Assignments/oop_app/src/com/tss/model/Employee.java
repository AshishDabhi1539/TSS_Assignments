package com.tss.model;

import java.time.LocalDate;
import java.time.Period;

public class Employee {

	private int employeeId;
	private String name;
	private LocalDate joiningDate;
	private double salary;
	LocalDate currentDate = LocalDate.now();
	
	public void setEmployeeId(int empId) {
		this.employeeId = empId;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}
	
	public LocalDate getJoiningDate() {
		return joiningDate;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public Period calculateExperience() {
		Period experience = Period.between(joiningDate, currentDate);
		return experience;
	}
	
	public double calculateBonus() {
		return (salary * 50)/100 ;
	}
	
	public void display() {
		System.out.println();
		System.out.println("EmpId : " + employeeId);
		System.out.println("Name : " + name);
		System.out.println("Joining Date : " + joiningDate);
		System.out.println("Salary : " + salary);
		System.out.println("Experience : " + calculateExperience().getYears() + " Years of experience");
		System.out.println("Bonus : " + calculateBonus());
	}
}
