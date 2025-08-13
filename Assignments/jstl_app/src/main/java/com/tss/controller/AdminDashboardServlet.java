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
import com.tss.model.LeaveRequest;
import com.tss.service.LeaveRequestService;

@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private LeaveRequestService leaveService;

	@Override
	public void init() throws ServletException {
		Connection conn = DBConnection.connect();
		leaveService = new LeaveRequestService(conn);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || !"ADMIN".equals(session.getAttribute("role"))) {
			response.sendRedirect("login.jsp");
			return;
		}

		try (Connection conn = DBConnection.connect()) { // open fresh connection
			LeaveRequestService leaveService = new LeaveRequestService(conn); // pass it here
			List<LeaveRequest> leaveRequests = leaveService.getAllRequests();

			request.setAttribute("leaveRequests", leaveRequests);
			request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
