package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tss.database.DBConnection;
import com.tss.model.Employee;

public class EmployeeDAO {

	// Get employee by username and password (login)
	public Employee getEmployee(String username, String password) throws SQLException {
		String sql = "SELECT * FROM employees WHERE username = ? AND password = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return mapResultSetToEmployee(rs);
			}
		}
		return null;
	}

	// Get employee by username only (check for registration)
	public Employee getEmployeeByUsername(String username) throws SQLException {
		String sql = "SELECT * FROM employees WHERE username = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return mapResultSetToEmployee(rs);
			}
		}
		return null;
	}

	// Add new employee to database
	public void addEmployee(Employee employee) throws SQLException {
		String sql = "INSERT INTO employees (username, password, role, leave_balance) VALUES (?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, employee.getUsername());
			stmt.setString(2, employee.getPassword());
			stmt.setString(3, employee.getRole());
			stmt.setInt(4, employee.getLeaveBalance());
			stmt.executeUpdate();
		}
	}

	// Update leave balance
	public void updateLeaveBalance(int employeeId, int days) throws SQLException {
		String sql = "UPDATE employees SET leave_balance = leave_balance - ? WHERE id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, days);
			stmt.setInt(2, employeeId);
			stmt.executeUpdate();
		}
	}

	private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
		return new Employee(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("role"),
				rs.getInt("leave_balance"));
	}
}
