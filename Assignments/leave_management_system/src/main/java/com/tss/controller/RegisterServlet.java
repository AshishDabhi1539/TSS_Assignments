package com.tss.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.model.Employee;
import com.tss.service.EmployeeService;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService = new EmployeeService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		if (!password.equals(confirmPassword)) {
			response.sendRedirect("register.jsp?error=nomatch");
			return;
		}

		Employee employee = new Employee(0, username, password, "employee",20);
		try {
			employeeService.register(employee);
			response.sendRedirect("login.jsp?success=registered");
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("register.jsp?error=exists");
		}
	}
}
