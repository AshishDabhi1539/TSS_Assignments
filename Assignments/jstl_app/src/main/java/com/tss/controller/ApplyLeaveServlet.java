package com.tss.controller;

import java.io.IOException;
import java.sql.Connection;
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

@WebServlet("/applyLeave")
public class ApplyLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// No need to initialize services with a static connection here
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

			int empId = (int) session.getAttribute("empId");
			String leaveType = req.getParameter("leaveType");
			LocalDate from = LocalDate.parse(req.getParameter("fromDate"));
			LocalDate to = LocalDate.parse(req.getParameter("toDate"));
			String reason = req.getParameter("reason");

			int days = (int) (to.toEpochDay() - from.toEpochDay()) + 1;
			if (days <= 0) {
				req.setAttribute("errorMessage", "Invalid date range");
				req.getRequestDispatcher("user-dashboard.jsp").forward(req, resp);
				return;
			}

			Integer remaining = 0;
			if (balanceService.getLeaveBalance(empId) != null) {
				remaining = balanceService.getLeaveBalance(empId).getRemainingLeaves();
			}

			if (remaining < days) {
				req.setAttribute("errorMessage", "Not enough leave balance");
				req.getRequestDispatcher("user-dashboard.jsp").forward(req, resp);
				return;
			}

			LeaveRequest lr = new LeaveRequest();
			lr.setEmpId(empId);
			lr.setLeaveType(leaveType);
			lr.setStartDate(from);
			lr.setEndDate(to);
			lr.setReason(reason);
			lr.setStatus("Pending");

			boolean ok = leaveService.addLeaveRequest(lr);

			if (ok) {
				resp.sendRedirect("UserDashboardServlet");
			} else {
				req.setAttribute("errorMessage", "Unable to apply leave");
				req.getRequestDispatcher("user-dashboard.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}