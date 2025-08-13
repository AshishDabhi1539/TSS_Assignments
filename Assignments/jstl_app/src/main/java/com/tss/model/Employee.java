package com.tss.model;

public class Employee {
	private int empId;
	private String name;
	private String email;
	private String password;
	private String jobTitle;
	private int deptNo;
	private String role;

	public Employee() {
	}

	public Employee(int empId, String name, String email, String password, String jobTitle, int deptNo, String role) {
		this.empId = empId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.jobTitle = jobTitle;
		this.deptNo = deptNo;
		this.role = role;
	}

	// Getters and setters

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
