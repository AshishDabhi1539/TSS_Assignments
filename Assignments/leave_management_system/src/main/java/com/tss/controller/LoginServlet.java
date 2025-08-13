package com.tss.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.model.Employee;
import com.tss.service.EmployeeService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService = new EmployeeService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			Employee employee = employeeService.login(username, password);
			if (employee != null) {
				HttpSession session = request.getSession();
				session.setAttribute("employee", employee);
				if ("admin".equals(employee.getRole())) {
					response.sendRedirect("adminDashboard");
				} else {
					response.sendRedirect("employeeDashboard");
				}
			} else {
				response.sendRedirect("login.jsp?error=invalid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("login.jsp?error=error");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}
}
