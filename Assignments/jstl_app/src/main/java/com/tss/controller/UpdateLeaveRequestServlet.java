package com.tss.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.database.DBConnection;
import com.tss.model.LeaveRequest;
import com.tss.service.LeaveBalanceService;
import com.tss.service.LeaveRequestService;

@WebServlet("/updateLeaveRequest")
public class UpdateLeaveRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null || !"USER".equals(session.getAttribute("role"))) {
			resp.sendRedirect("login.jsp");
			return;
		}

		try (Connection conn = DBConnection.connect()) {
			LeaveRequestService leaveService = new LeaveRequestService(conn);
			int requestId = Integer.parseInt(req.getParameter("requestId"));
			LeaveRequest leaveRequest = leaveService.getRequestById(requestId);

			if (leaveRequest != null && leaveRequest.getStatus().equalsIgnoreCase("Pending")) {
				req.setAttribute("leaveRequest", leaveRequest);
				req.getRequestDispatcher("update-leave-request.jsp").forward(req, resp);
			} else {
				resp.sendRedirect("leave-error.jsp?error=Cannot edit this leave request.");
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null || !"USER".equals(session.getAttribute("role"))) {
			resp.sendRedirect("login.jsp");
			return;
		}

		try (Connection conn = DBConnection.connect()) {
			LeaveRequestService leaveService = new LeaveRequestService(conn);
			LeaveBalanceService balanceService = new LeaveBalanceService(conn);

			int requestId = Integer.parseInt(req.getParameter("requestId"));
			int empId = (int) session.getAttribute("empId");
			String leaveType = req.getParameter("leaveType");
			LocalDate from = LocalDate.parse(req.getParameter("fromDate"));
			LocalDate to = LocalDate.parse(req.getParameter("toDate"));
			String reason = req.getParameter("reason");

			// Validate past dates
			if (from.isBefore(LocalDate.now())) {
				resp.sendRedirect("leave-error.jsp?error=Cannot select past dates for leave.");
				return;
			}

			// Validate date range
			int days = (int) (to.toEpochDay() - from.toEpochDay()) + 1;
			if (days <= 0) {
				resp.sendRedirect("leave-error.jsp?error=Invalid date range: End date must be after start date.");
				return;
			}

			// Validate leave balance
			Integer remaining = balanceService.getLeaveBalance(empId) != null
					? balanceService.getLeaveBalance(empId).getRemainingLeaves()
					: 0;
			if (remaining < days) {
				resp.sendRedirect("leave-error.jsp?error=Not enough leave balance.");
				return;
			}

			LeaveRequest lr = new LeaveRequest();
			lr.setRequestId(requestId);
			lr.setEmpId(empId);
			lr.setLeaveType(leaveType);
			lr.setStartDate(from);
			lr.setEndDate(to);
			lr.setReason(reason);
			lr.setStatus("Pending");

			try {
				boolean ok = leaveService.updateLeaveRequest(lr);
				if (ok) {
					resp.sendRedirect("UserDashboardServlet");
				} else {
					resp.sendRedirect("leave-error.jsp?error=Unable to update leave request due to a system error.");
				}
			} catch (SQLException e) {
				resp.sendRedirect("leave-error.jsp?error=" + e.getMessage());
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}