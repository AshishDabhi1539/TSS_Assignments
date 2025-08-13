package com.tss.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.dao.EmployeeDAO;
import com.tss.database.DBConnection;
import com.tss.model.Employee;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();

		try (Connection conn = DBConnection.connect()) {
			// Hardcoded admin check
			if ("admin".equals(email) && "admin123".equals(password)) {
				HttpSession session = request.getSession();
				session.setAttribute("role", "ADMIN");
				session.setAttribute("name", "Admin");
				response.sendRedirect("AdminDashboardServlet");
				return;
			}

			EmployeeDAO dao = new EmployeeDAO(conn);
			Employee emp = dao.getEmployeeByEmail(email);

			if (emp != null && emp.getPassword().equals(password)
					&& ("USER".equalsIgnoreCase(emp.getRole()) || "EMPLOYEE".equalsIgnoreCase(emp.getRole()))) {

				HttpSession session = request.getSession();
				session.setAttribute("role", "USER");
				session.setAttribute("empId", emp.getEmpId());
				session.setAttribute("name", emp.getName());
				response.sendRedirect("UserDashboardServlet");
			} else {
				request.setAttribute("errorMessage", "Invalid credentials");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
