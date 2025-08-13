package com.tss.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.model.Employee;
import com.tss.model.LeaveRequest;
import com.tss.service.LeaveService;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LeaveService leaveService = new LeaveService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee admin = (Employee) request.getSession().getAttribute("employee");
		if (admin == null || !"admin".equals(admin.getRole())) {
			response.sendRedirect("login.jsp");
			return;
		}

		try {
			List<LeaveRequest> pending = leaveService.getPendingRequests();
			request.setAttribute("requests", pending);
			request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("adminDashboard.jsp?error=error");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee admin = (Employee) request.getSession().getAttribute("employee");
		if (admin == null || !"admin".equals(admin.getRole())) {
			response.sendRedirect("login.jsp");
			return;
		}

		try {
			int leaveId = Integer.parseInt(request.getParameter("id"));
			String action = request.getParameter("action");
			leaveService.approveRejectLeave(leaveId, admin.getId(), action);
			response.sendRedirect("adminDashboard");
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("adminDashboard.jsp?error=error");
		}
	}
}
