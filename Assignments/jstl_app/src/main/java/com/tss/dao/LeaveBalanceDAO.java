package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tss.model.LeaveBalance;

public class LeaveBalanceDAO {
	private final Connection conn;

	public LeaveBalanceDAO(Connection conn) {
		this.conn = conn;
	}

	public boolean addLeaveBalance(int empId, int totalLeaves) throws SQLException {
		String sql = "INSERT INTO leave_balance (emp_id, total_leaves, leaves_taken) VALUES (?, ?, 0)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, empId);
			ps.setInt(2, totalLeaves);
			return ps.executeUpdate() > 0;
		}
	}

	public LeaveBalance getLeaveBalance(int empId) throws SQLException {
		String sql = "SELECT * FROM leave_balance WHERE emp_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, empId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					LeaveBalance lb = new LeaveBalance();
					lb.setEmpId(empId);
					lb.setTotalLeaves(rs.getInt("total_leaves"));
					lb.setLeavesTaken(rs.getInt("leaves_taken"));
					return lb;
				}
			}
		}
		return null;
	}

	public boolean updateLeavesTaken(int empId, int leavesTaken) throws SQLException {
		String sql = "UPDATE leave_balance SET leaves_taken = ? WHERE emp_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, leavesTaken);
			ps.setInt(2, empId);
			return ps.executeUpdate() > 0;
		}
	}
}
