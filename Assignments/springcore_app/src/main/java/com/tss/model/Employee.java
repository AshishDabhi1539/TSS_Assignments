package com.tss.model;

public class Employee {
	private String name;
	private Department department;

	public Employee() {
		super();
	}

	public Employee(String name, Department department) {
		super();
		this.name = name;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void showDetails() {
		System.out.println("Employee: " + name + ", " + department);
	}

}
