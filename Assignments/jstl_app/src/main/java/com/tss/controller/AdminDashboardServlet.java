package com.tss.controller;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.database.DBConnection;
import com.tss.model.LeaveRequest;
import com.tss.service.LeaveRequestService;

@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || !"ADMIN".equals(session.getAttribute("role"))) {
			response.sendRedirect("login.jsp");
			return;
		}

		try (Connection conn = DBConnection.connect()) {
			LeaveRequestService leaveService = new LeaveRequestService(conn);
			String startDateStr = request.getParameter("startDate");
			String endDateStr = request.getParameter("endDate");
			String status = request.getParameter("status");

			List<LeaveRequest> leaveRequests;
			if (startDateStr != null && !startDateStr.isEmpty() && endDateStr != null && !endDateStr.isEmpty()) {
				LocalDate startDate = LocalDate.parse(startDateStr);
				LocalDate endDate = LocalDate.parse(endDateStr);
				leaveRequests = leaveService.getRequestsByDateRangeAndStatus(startDate, endDate, status);
			} else {
				leaveRequests = leaveService.getAllRequests();
			}

			request.setAttribute("leaveRequests", leaveRequests);
			//request.getRequestDispatcher("AdminDashboardServlet").forward(request, response);
			request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}