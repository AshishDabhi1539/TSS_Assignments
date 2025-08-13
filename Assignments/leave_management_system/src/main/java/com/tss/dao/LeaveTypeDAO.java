package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tss.database.DBConnection;
import com.tss.model.LeaveType;

public class LeaveTypeDAO {

	public List<LeaveType> getAllLeaveTypes() throws SQLException {
		String sql = "SELECT * FROM leave_types";
		List<LeaveType> types = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				types.add(new LeaveType(rs.getInt("id"), rs.getString("name"), rs.getInt("default_days")));
			}
		}
		return types;
	}
}
