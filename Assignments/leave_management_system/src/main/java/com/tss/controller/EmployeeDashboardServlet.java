package com.tss.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.model.Employee;
import com.tss.model.LeaveRequest;
import com.tss.model.LeaveType;
import com.tss.service.LeaveService;

@WebServlet("/employeeDashboard")
public class EmployeeDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LeaveService leaveService = new LeaveService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		if (employee == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		try {
			List<LeaveType> types = leaveService.getLeaveTypes();
			List<LeaveRequest> history = leaveService.getLeaveHistory(employee.getId());
			request.setAttribute("leaveTypes", types);
			request.setAttribute("leaveHistory", history);
			request.getRequestDispatcher("employeeDashboard.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("employeeDashboard.jsp?error=error");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		if (employee == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		try {
			int typeId = Integer.parseInt(request.getParameter("typeId"));
			String startDateStr = request.getParameter("startDate");
			String endDateStr = request.getParameter("endDate");
			String reason = request.getParameter("reason");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = sdf.parse(startDateStr);
			Date endDate = sdf.parse(endDateStr);

			LeaveRequest leave = new LeaveRequest(0, employee.getId(), typeId, startDate, endDate, null, "pending",
					reason);
			leaveService.submitLeaveRequest(leave);
			response.sendRedirect("employeeDashboard");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("employeeDashboard.jsp?error=error");
		}
	}
}
