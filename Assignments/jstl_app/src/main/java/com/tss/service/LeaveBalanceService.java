package com.tss.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.tss.dao.LeaveBalanceDAO;
import com.tss.model.LeaveBalance;

public class LeaveBalanceService {
    private LeaveBalanceDAO leaveBalanceDAO;

    public LeaveBalanceService(Connection conn) {
        this.leaveBalanceDAO = new LeaveBalanceDAO(conn);
    }

    public boolean addLeaveBalance(int empId, int totalLeaves) throws SQLException {
        return leaveBalanceDAO.addLeaveBalance(empId, totalLeaves);
    }

    public LeaveBalance getLeaveBalance(int empId) throws SQLException {
        return leaveBalanceDAO.getLeaveBalance(empId);
    }

    public boolean updateLeavesTaken(int empId, int leavesTaken) throws SQLException {
        return leaveBalanceDAO.updateLeavesTaken(empId, leavesTaken);
    }
}
