package com.tss.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.tss.model.LeaveRequest;

public class LeaveRequestDAO {
	private final Connection conn;

	public LeaveRequestDAO(Connection conn) {
		this.conn = conn;
	}

	public boolean addLeaveRequest(LeaveRequest req) throws SQLException {
		String sql = "INSERT INTO leave_request (emp_id, leave_type, start_date, end_date, reason, status, rejection_reason) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, req.getEmpId());
			ps.setString(2, req.getLeaveType());
			ps.setDate(3, req.getStartDate() != null ? Date.valueOf(req.getStartDate()) : null);
			ps.setDate(4, req.getEndDate() != null ? Date.valueOf(req.getEndDate()) : null);
			ps.setString(5, req.getReason());
			ps.setString(6, req.getStatus());
			ps.setString(7, req.getRejectionReason());
			return ps.executeUpdate() > 0;
		}
	}

	public List<LeaveRequest> getAllRequests() throws SQLException {
		String sql = "SELECT * FROM leave_request ORDER BY request_date DESC"; // Sort by newest first
		List<LeaveRequest> requests = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				requests.add(extractLeaveRequestFromResultSet(rs));
			}
		}
		return requests;
	}

	public List<LeaveRequest> getRequestsByEmpId(int empId) throws SQLException {
		String sql = "SELECT * FROM leave_request WHERE emp_id = ? ORDER BY request_date DESC";
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

	public boolean updateLeaveStatus(int requestId, String status, String rejectionReason) throws SQLException {
		String sql = "UPDATE leave_request SET status = ?, rejection_reason = ? WHERE request_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, status);
			ps.setString(2, rejectionReason);
			ps.setInt(3, requestId);
			return ps.executeUpdate() > 0;
		}
	}

	public boolean updateLeaveRequest(LeaveRequest req) throws SQLException {
		String sql = "UPDATE leave_request SET leave_type = ?, start_date = ?, end_date = ?, reason = ? WHERE request_id = ? AND status = 'Pending'";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, req.getLeaveType());
			ps.setDate(2, req.getStartDate() != null ? Date.valueOf(req.getStartDate()) : null);
			ps.setDate(3, req.getEndDate() != null ? Date.valueOf(req.getEndDate()) : null);
			ps.setString(4, req.getReason());
			ps.setInt(5, req.getRequestId());
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

	public int countLeavesInMonth(int empId, LocalDate startDate) throws SQLException {
		String sql = "SELECT COUNT(*) FROM leave_request WHERE emp_id = ? AND MONTH(start_date) = ? AND YEAR(start_date) = ? AND status != 'Rejected'";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, empId);
			ps.setInt(2, startDate.getMonthValue());
			ps.setInt(3, startDate.getYear());
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		}
		return 0;
	}

	public boolean hasOverlappingLeave(int empId, LocalDate startDate, LocalDate endDate, int excludeRequestId)
			throws SQLException {
		String sql = "SELECT COUNT(*) FROM leave_request WHERE emp_id = ? AND status != 'Rejected' AND request_id != ? AND "
				+ "((start_date <= ? AND end_date >= ?) OR (start_date <= ? AND end_date >= ?) OR (start_date >= ? AND end_date <= ?))";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, empId);
			ps.setInt(2, excludeRequestId);
			ps.setDate(3, Date.valueOf(endDate));
			ps.setDate(4, Date.valueOf(startDate));
			ps.setDate(5, Date.valueOf(endDate));
			ps.setDate(6, Date.valueOf(startDate));
			ps.setDate(7, Date.valueOf(startDate));
			ps.setDate(8, Date.valueOf(endDate));
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		}
		return false;
	}

	public List<LeaveRequest> getRequestsByDateRangeAndStatus(LocalDate startDate, LocalDate endDate, String status)
			throws SQLException {
		String sql = "SELECT * FROM leave_request WHERE start_date >= ? AND end_date <= ?"
				+ (status != null && !status.isEmpty() ? " AND status = ?" : "") + " ORDER BY request_date DESC";
		List<LeaveRequest> requests = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setDate(1, Date.valueOf(startDate));
			ps.setDate(2, Date.valueOf(endDate));
			if (status != null && !status.isEmpty()) {
				ps.setString(3, status);
			}
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					requests.add(extractLeaveRequestFromResultSet(rs));
				}
			}
		}
		return requests;
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
		lr.setRejectionReason(rs.getString("rejection_reason")); // New field
		return lr;
	}
}