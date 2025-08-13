package com.tss.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.database.DBConnection;
import com.tss.model.Employee;
import com.tss.service.EmployeeService;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// No static connection initialization
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection conn = DBConnection.connect()) {
			EmployeeService tempService = new EmployeeService(conn);
			List<Employee> employees = tempService.getAllEmployees();
			request.setAttribute("employees", employees);
			request.getRequestDispatcher("/employee-list.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", "Error fetching employees: " + e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection conn = DBConnection.connect()) {
			EmployeeService tempService = new EmployeeService(conn);

			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String jobTitle = request.getParameter("jobTitle");
			int deptNo = Integer.parseInt(request.getParameter("deptNo"));
			String role = request.getParameter("role");

			if (!"EMPLOYEE".equalsIgnoreCase(role) && !"ADMIN".equalsIgnoreCase(role)) {
				request.setAttribute("error", "Invalid role. Use EMPLOYEE or ADMIN.");
				request.getRequestDispatcher("/employee-form.jsp").forward(request, response);
				return;
			}

			Employee emp = new Employee();
			emp.setName(name);
			emp.setEmail(email);
			emp.setPassword(password);
			emp.setJobTitle(jobTitle);
			emp.setDeptNo(deptNo);
			emp.setRole(role.toUpperCase());

			int empId = tempService.addEmployee(emp);

			if (empId > 0) {
				response.sendRedirect("employees");
			} else {
				request.setAttribute("error", "Unable to add employee. Please try again.");
				request.getRequestDispatcher("/employee-form.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			request.setAttribute("error", "Invalid department number format.");
			request.getRequestDispatcher("/employee-form.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", "Error adding employee: " + e.getMessage());
			request.getRequestDispatcher("/employee-form.jsp").forward(request, response);
		}
	}

	@Override
	public void destroy() {
		// No cleanup needed for dynamic connections
	}
}