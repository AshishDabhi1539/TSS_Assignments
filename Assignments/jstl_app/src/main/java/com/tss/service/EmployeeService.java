package com.tss.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.tss.dao.EmployeeDAO;
import com.tss.model.Employee;

public class EmployeeService {
	private EmployeeDAO employeeDAO;

	// Constructor accepting existing connection
	public EmployeeService(Connection conn) {
		this.employeeDAO = new EmployeeDAO(conn);
	}

	// Add employee and return generated empId (or -1 if fail)
	public int addEmployee(Employee emp) throws SQLException {
		return employeeDAO.addEmployee(emp);
	}

	// Get employee by email
	public Employee getEmployeeByEmail(String email) throws SQLException {
		return employeeDAO.getEmployeeByEmail(email);
	}

	// Get all employees
	public List<Employee> getAllEmployees() throws SQLException {
		return employeeDAO.getAllEmployees();
	}

	// Delete employee by empId
	public boolean deleteEmployee(int empId) throws SQLException {
		return employeeDAO.deleteEmployee(empId);
	}
}
