package com.tss.controller;

import java.io.IOException;
import java.sql.Connection;

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

@WebServlet("/updateLeaveStatus")
public class UpdateLeaveStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// No static connection initialization
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null || !"ADMIN".equals(session.getAttribute("role"))) {
			resp.sendRedirect("login.jsp");
			return;
		}

		try (Connection conn = DBConnection.connect()) {
			LeaveRequestService tempLeaveService = new LeaveRequestService(conn);
			LeaveBalanceService tempBalanceService = new LeaveBalanceService(conn);

			int leaveId = Integer.parseInt(req.getParameter("leaveId"));
			String status = req.getParameter("status"); // APPROVED or REJECTED

			boolean updated = tempLeaveService.updateLeaveStatus(leaveId, status);

			if (updated && "APPROVED".equalsIgnoreCase(status)) {
				LeaveRequest lr = tempLeaveService.getRequestById(leaveId);
				if (lr != null) {
					int days = (int) (lr.getEndDate().toEpochDay() - lr.getStartDate().toEpochDay()) + 1;
					if (days <= 0) {
						throw new ServletException("Invalid date range in leave request");
					}

					com.tss.model.LeaveBalance lb = tempBalanceService.getLeaveBalance(lr.getEmpId());
					if (lb != null) {
						int newTaken = lb.getLeavesTaken() + days;
						tempBalanceService.updateLeavesTaken(lr.getEmpId(), newTaken);
					} else {
						tempBalanceService.addLeaveBalance(lr.getEmpId(), 20);
						tempBalanceService.updateLeavesTaken(lr.getEmpId(), days);
					}
				}
			}

			resp.sendRedirect("AdminDashboardServlet");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}