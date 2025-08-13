package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tss.model.Employee;

public class EmployeeDAO {
	private final Connection conn;

	public EmployeeDAO(Connection conn) {
		this.conn = conn;
	}

	public int addEmployee(Employee emp) throws SQLException {
		String sql = "INSERT INTO employee (name, email, password, job_title, dept_no, role) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getEmail());
			ps.setString(3, emp.getPassword());
			ps.setString(4, emp.getJobTitle());
			ps.setInt(5, emp.getDeptNo());
			ps.setString(6, emp.getRole());
			int affectedRows = ps.executeUpdate();
			if (affectedRows == 0) {
				return -1;
			}
			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getInt(1);
				} else {
					return -1;
				}
			}
		}
	}

	public Employee getEmployeeByEmail(String email) throws SQLException {
		String sql = "SELECT * FROM employee WHERE email = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return extractEmployeeFromResultSet(rs);
				}
			}
		}
		return null;
	}

	public List<Employee> getAllEmployees() throws SQLException {
		String sql = "SELECT * FROM employee";
		List<Employee> employees = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				employees.add(extractEmployeeFromResultSet(rs));
			}
		}
		return employees;
	}

	public boolean deleteEmployee(int empId) throws SQLException {
		String sql = "DELETE FROM employee WHERE emp_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, empId);
			return ps.executeUpdate() > 0;
		}
	}

	private Employee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {
		Employee emp = new Employee();
		emp.setEmpId(rs.getInt("emp_id"));
		emp.setName(rs.getString("name"));
		emp.setEmail(rs.getString("email"));
		emp.setPassword(rs.getString("password"));
		emp.setJobTitle(rs.getString("job_title"));
		emp.setDeptNo(rs.getInt("dept_no"));
		emp.setRole(rs.getString("role"));
		return emp;
	}
}
