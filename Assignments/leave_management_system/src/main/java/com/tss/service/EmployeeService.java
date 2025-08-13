package com.tss.service;

import java.sql.SQLException;

import com.tss.dao.EmployeeDAO;
import com.tss.model.Employee;

public class EmployeeService {
	private EmployeeDAO employeeDAO = new EmployeeDAO();

	// Login method
	public Employee login(String username, String password) throws SQLException {
		return employeeDAO.getEmployee(username, password);
	}

	// Register a new employee
	public void register(Employee employee) throws SQLException {
		// Check if username already exists
		if (employeeDAO.getEmployeeByUsername(employee.getUsername()) != null) {
			throw new SQLException("Username already exists!");
		}
		// Save new employee
		employeeDAO.addEmployee(employee);
	}

	// Deduct leave balance after approval
	public void deductLeaveBalance(int employeeId, int days) throws SQLException {
		employeeDAO.updateLeaveBalance(employeeId, days);
	}
}
