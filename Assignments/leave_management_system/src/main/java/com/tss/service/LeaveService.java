package com.tss.service;

import java.sql.SQLException;
import java.util.List;

import com.tss.dao.LeaveDAO;
import com.tss.dao.LeaveTypeDAO;
import com.tss.model.LeaveRequest;
import com.tss.model.LeaveType;

public class LeaveService {
	private LeaveDAO leaveDAO = new LeaveDAO();
	private EmployeeService employeeService = new EmployeeService();
	private LeaveTypeDAO leaveTypeDAO = new LeaveTypeDAO();

	public void submitLeaveRequest(LeaveRequest leave) throws SQLException {
		leaveDAO.submitLeaveRequest(leave);
	}

	public List<LeaveRequest> getPendingRequests() throws SQLException {
		return leaveDAO.getPendingRequests();
	}

	public List<LeaveRequest> getLeaveHistory(int employeeId) throws SQLException {
		return leaveDAO.getLeavesByEmployee(employeeId);
	}

	public List<LeaveType> getLeaveTypes() throws SQLException {
		return leaveTypeDAO.getAllLeaveTypes();
	}

	public void approveRejectLeave(int leaveId, int adminId, String status) throws SQLException {
		LeaveRequest leave = leaveDAO.getLeaveById(leaveId);
		if (leave == null)
			throw new SQLException("Leave not found");

		leaveDAO.updateLeaveStatus(leaveId, status);

		if ("approved".equalsIgnoreCase(status)) {
			int days = (int) ((leave.getEndDate().getTime() - leave.getStartDate().getTime()) / (1000 * 60 * 60 * 24))
					+ 1;
			employeeService.deductLeaveBalance(leave.getEmployeeId(), days);
		}

		leaveDAO.logAdminAction(adminId, leaveId, status);
	}
}
