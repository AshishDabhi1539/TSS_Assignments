package com.tss.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tss.model.LeaveRequest;

public class LeaveRequestDAO {
	private final Connection conn;

	public LeaveRequestDAO(Connection conn) {
		this.conn = conn;
	}

	public boolean addLeaveRequest(LeaveRequest req) throws SQLException {
		String sql = "INSERT INTO leave_request (emp_id, leave_type, start_date, end_date, reason, status) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, req.getEmpId());
			ps.setString(2, req.getLeaveType());
			ps.setDate(3, req.getStartDate() != null ? Date.valueOf(req.getStartDate()) : null);
			ps.setDate(4, req.getEndDate() != null ? Date.valueOf(req.getEndDate()) : null);
			ps.setString(5, req.getReason());
			ps.setString(6, req.getStatus());
			return ps.executeUpdate() > 0;
		}
	}

	public List<LeaveRequest> getAllRequests() throws SQLException {
		String sql = "SELECT * FROM leave_request";
		List<LeaveRequest> requests = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				requests.add(extractLeaveRequestFromResultSet(rs));
			}
		}
		return requests;
	}

	public List<LeaveRequest> getRequestsByEmpId(int empId) throws SQLException {
		String sql = "SELECT * FROM leave_request WHERE emp_id = ?";
		List<LeaveRequest> requests = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, empId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					requests.add(extractLeaveRequestFromResultSet(rs));
				}
			}
		}
		return requests;
	}

	public boolean updateLeaveStatus(int requestId, String status) throws SQLException {
		String sql = "UPDATE leave_request SET status = ? WHERE request_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, status);
			ps.setInt(2, requestId);
			return ps.executeUpdate() > 0;
		}
	}

	public LeaveRequest getRequestById(int requestId) throws SQLException {
		String sql = "SELECT * FROM leave_request WHERE request_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, requestId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return extractLeaveRequestFromResultSet(rs);
				}
			}
		}
		return null;
	}

	private LeaveRequest extractLeaveRequestFromResultSet(ResultSet rs) throws SQLException {
		LeaveRequest lr = new LeaveRequest();
		lr.setRequestId(rs.getInt("request_id"));
		lr.setEmpId(rs.getInt("emp_id"));
		lr.setLeaveType(rs.getString("leave_type"));
		lr.setStartDate(rs.getDate("start_date") != null ? rs.getDate("start_date").toLocalDate() : null);
		lr.setEndDate(rs.getDate("end_date") != null ? rs.getDate("end_date").toLocalDate() : null);
		lr.setReason(rs.getString("reason"));
		lr.setStatus(rs.getString("status"));
		return lr;
	}
}
