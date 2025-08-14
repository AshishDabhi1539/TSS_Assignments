package com.tss.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.tss.dao.LeaveRequestDAO;
import com.tss.model.LeaveRequest;

public class LeaveRequestService {
	private LeaveRequestDAO leaveRequestDAO;

	public LeaveRequestService(Connection conn) {
		this.leaveRequestDAO = new LeaveRequestDAO(conn);
	}

	public boolean addLeaveRequest(LeaveRequest req) throws SQLException {
		// Check leave count for the month
		if (leaveRequestDAO.countLeavesInMonth(req.getEmpId(), req.getStartDate()) >= 3) {
			throw new SQLException("Cannot apply more than 3 leaves in a month.");
		}
		// Check for overlapping dates
		if (leaveRequestDAO.hasOverlappingLeave(req.getEmpId(), req.getStartDate(), req.getEndDate(), 0)) {
			throw new SQLException("Leave request overlaps with an existing approved or pending request.");
		}
		return leaveRequestDAO.addLeaveRequest(req);
	}

	public boolean updateLeaveRequest(LeaveRequest req) throws SQLException {
		// Check for overlapping dates (exclude current request)
		if (leaveRequestDAO.hasOverlappingLeave(req.getEmpId(), req.getStartDate(), req.getEndDate(),
				req.getRequestId())) {
			throw new SQLException("Updated leave request overlaps with an existing approved or pending request.");
		}
		return leaveRequestDAO.updateLeaveRequest(req);
	}

	public List<LeaveRequest> getAllRequests() throws SQLException {
		return leaveRequestDAO.getAllRequests();
	}

	public List<LeaveRequest> getRequestsByEmpId(int empId) throws SQLException {
		return leaveRequestDAO.getRequestsByEmpId(empId);
	}

	public boolean updateLeaveStatus(int requestId, String status, String rejectionReason) throws SQLException {
		return leaveRequestDAO.updateLeaveStatus(requestId, status, rejectionReason);
	}

	public LeaveRequest getRequestById(int requestId) throws SQLException {
		return leaveRequestDAO.getRequestById(requestId);
	}

	public List<LeaveRequest> getRequestsByDateRangeAndStatus(LocalDate startDate, LocalDate endDate, String status)
			throws SQLException {
		return leaveRequestDAO.getRequestsByDateRangeAndStatus(startDate, endDate, status);
	}
}