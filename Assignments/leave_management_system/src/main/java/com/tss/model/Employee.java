package com.tss.model;

public class Employee {
	private int id;
	private String username;
	private String password;
	private String role;
	private int leaveBalance; // New field

	// Constructor for new registration (id = 0 initially)
	public Employee(int id, String username, String password, String role, int leaveBalance) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.leaveBalance = leaveBalance;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getLeaveBalance() {
		return leaveBalance;
	}

	public void setLeaveBalance(int leaveBalance) {
		this.leaveBalance = leaveBalance;
	}
}
