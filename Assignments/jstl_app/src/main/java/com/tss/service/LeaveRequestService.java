package com.tss.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.tss.dao.LeaveRequestDAO;
import com.tss.model.LeaveRequest;

public class LeaveRequestService {
	private LeaveRequestDAO leaveRequestDAO;

	public LeaveRequestService(Connection conn) {
		this.leaveRequestDAO = new LeaveRequestDAO(conn);
	}

	public boolean addLeaveRequest(LeaveRequest req) throws SQLException {
		return leaveRequestDAO.addLeaveRequest(req);
	}

	public List<LeaveRequest> getAllRequests() throws SQLException {
		return leaveRequestDAO.getAllRequests();
	}

	public List<LeaveRequest> getRequestsByEmpId(int empId) throws SQLException {
		return leaveRequestDAO.getRequestsByEmpId(empId);
	}

	public boolean updateLeaveStatus(int requestId, String status) throws SQLException {
		return leaveRequestDAO.updateLeaveStatus(requestId, status);
	}

	public LeaveRequest getRequestById(int requestId) throws SQLException {
		return leaveRequestDAO.getRequestById(requestId);
	}
}
