package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tss.database.DBConnection;
import com.tss.model.LeaveRequest;

public class LeaveDAO {

	public void submitLeaveRequest(LeaveRequest leave) throws SQLException {
		String sql = "INSERT INTO leave_requests (employee_id, type_id, start_date, end_date, status, reason) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, leave.getEmployeeId());
			stmt.setInt(2, leave.getTypeId());
			stmt.setDate(3, new java.sql.Date(leave.getStartDate().getTime()));
			stmt.setDate(4, new java.sql.Date(leave.getEndDate().getTime()));
			stmt.setString(5, leave.getStatus());
			stmt.setString(6, leave.getReason());
			stmt.executeUpdate();
		}
	}

	public List<LeaveRequest> getPendingRequests() throws SQLException {
		String sql = "SELECT lr.*, lt.name as type_name FROM leave_requests lr JOIN leave_types lt ON lr.type_id = lt.id WHERE lr.status = 'pending'";
		List<LeaveRequest> requests = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				requests.add(mapResultSetToLeaveRequest(rs));
			}
		}
		return requests;
	}

	public List<LeaveRequest> getLeavesByEmployee(int employeeId) throws SQLException {
		String sql = "SELECT lr.*, lt.name as type_name FROM leave_requests lr JOIN leave_types lt ON lr.type_id = lt.id WHERE lr.employee_id = ?";
		List<LeaveRequest> leaves = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				leaves.add(mapResultSetToLeaveRequest(rs));
			}
		}
		return leaves;
	}

	public LeaveRequest getLeaveById(int id) throws SQLException {
		String sql = "SELECT lr.*, lt.name as type_name FROM leave_requests lr JOIN leave_types lt ON lr.type_id = lt.id WHERE lr.id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return mapResultSetToLeaveRequest(rs);
		}
		return null;
	}

	public void updateLeaveStatus(int id, String status) throws SQLException {
		String sql = "UPDATE leave_requests SET status = ? WHERE id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, status);
			stmt.setInt(2, id);
			stmt.executeUpdate();
		}
	}

	public void logAdminAction(int adminId, int leaveId, String action) throws SQLException {
		String sql = "INSERT INTO audit_logs (admin_id, leave_id, action) VALUES (?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, adminId);
			stmt.setInt(2, leaveId);
			stmt.setString(3, action);
			stmt.executeUpdate();
		}
	}

	private LeaveRequest mapResultSetToLeaveRequest(ResultSet rs) throws SQLException {
		return new LeaveRequest(rs.getInt("id"), rs.getInt("employee_id"), rs.getInt("type_id"),
				rs.getString("type_name"), rs.getDate("start_date"), rs.getDate("end_date"), rs.getDate("applied_on"),
				rs.getString("status"), rs.getString("reason"));
	}
}
