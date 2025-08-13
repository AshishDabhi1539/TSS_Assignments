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

import com.tss.database.DBConnection;
import com.tss.model.LeaveRequest;
import com.tss.service.LeaveRequestService;

@WebServlet("/leave-requests")
public class LeaveRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LeaveRequestServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		// No static connection initialization
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection conn = DBConnection.connect()) {
			LeaveRequestService tempService = new LeaveRequestService(conn);
			List<LeaveRequest> requests = tempService.getAllRequests();
			request.setAttribute("leaveRequests", requests);
			request.getRequestDispatcher("/leave-request-list.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException("Error fetching leave requests", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection conn = DBConnection.connect()) {
			LeaveRequestService tempService = new LeaveRequestService(conn);

			int empId = Integer.parseInt(request.getParameter("empId"));
			String leaveType = request.getParameter("leaveType");
			LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
			LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
			String reason = request.getParameter("reason");

			LeaveRequest req = new LeaveRequest();
			req.setEmpId(empId);
			req.setLeaveType(leaveType);
			req.setStartDate(startDate);
			req.setEndDate(endDate);
			req.setReason(reason);
			req.setStatus("PENDING");

			boolean success = tempService.addLeaveRequest(req);

			if (success) {
				response.sendRedirect("leave-requests");
			} else {
				request.setAttribute("error", "Unable to submit leave request.");
				request.getRequestDispatcher("/leave-request-form.jsp").forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException("Error adding leave request", e);
		}
	}
}