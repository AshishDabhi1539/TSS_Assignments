package com.tss.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.database.DBConnection;
import com.tss.model.LeaveBalance;
import com.tss.model.LeaveRequest;
import com.tss.service.LeaveBalanceService;
import com.tss.service.LeaveRequestService;

@WebServlet("/UserDashboardServlet")
public class UserDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || !"USER".equals(session.getAttribute("role"))) {
			response.sendRedirect("login.jsp");
			return;
		}

		int empId = (int) session.getAttribute("empId");

		try (Connection conn = DBConnection.connect()) {
			LeaveRequestService leaveService = new LeaveRequestService(conn);
			LeaveBalanceService balanceService = new LeaveBalanceService(conn);

			LeaveBalance lb = balanceService.getLeaveBalance(empId);
			request.setAttribute("leaveBalance", lb != null ? lb.getRemainingLeaves() : 0);

			List<LeaveRequest> myLeaves = leaveService.getRequestsByEmpId(empId);
			request.setAttribute("myLeaves", myLeaves);

			request.getRequestDispatcher("user-dashboard.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
