package com.tss.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.database.DBConnection;
import com.tss.model.LeaveBalance;
import com.tss.service.LeaveBalanceService;

@WebServlet("/leave-balance")
public class LeaveBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LeaveBalanceServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		// No static connection initialization
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection conn = DBConnection.connect()) {
			LeaveBalanceService tempService = new LeaveBalanceService(conn);
			int empId = Integer.parseInt(request.getParameter("empId"));
			LeaveBalance balance = tempService.getLeaveBalance(empId);
			request.setAttribute("leaveBalance", balance);
			request.getRequestDispatcher("/leave-balance.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException("Error fetching leave balance", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection conn = DBConnection.connect()) {
			LeaveBalanceService tempService = new LeaveBalanceService(conn);

			int empId = Integer.parseInt(request.getParameter("empId"));
			int totalLeaves = Integer.parseInt(request.getParameter("totalLeaves"));

			boolean success = tempService.addLeaveBalance(empId, totalLeaves);

			if (success) {
				response.sendRedirect("leave-balance?empId=" + empId);
			} else {
				request.setAttribute("error", "Unable to update leave balance.");
				request.getRequestDispatcher("/leave-balance-form.jsp").forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException("Error updating leave balance", e);
		}
	}
}