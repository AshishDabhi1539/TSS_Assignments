package com.tss.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.database.DBConnection;
import com.tss.model.Employee;
import com.tss.service.EmployeeService;
import com.tss.service.LeaveBalanceService;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// No static connection initialization
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (Connection conn = DBConnection.connect()) {
			EmployeeService tempEmployeeService = new EmployeeService(conn);
			LeaveBalanceService tempBalanceService = new LeaveBalanceService(conn);

			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String jobTitle = req.getParameter("jobTitle");
			int deptNo = Integer.parseInt(req.getParameter("deptNo"));

			Employee existing = tempEmployeeService.getEmployeeByEmail(email);
			if (existing != null) {
				req.setAttribute("errorMessage", "Email already registered");
				req.getRequestDispatcher("register.jsp").forward(req, resp);
				return;
			}

			Employee emp = new Employee();
			emp.setName(name);
			emp.setEmail(email);
			emp.setPassword(password);
			emp.setJobTitle(jobTitle);
			emp.setDeptNo(deptNo);
			emp.setRole("EMPLOYEE");

			int empId = tempEmployeeService.addEmployee(emp);
			if (empId > 0) {
				boolean balanceAdded = tempBalanceService.addLeaveBalance(empId, 20);
				if (balanceAdded) {
					req.setAttribute("successMessage", "Registration successful. Please login.");
					req.getRequestDispatcher("login.jsp").forward(req, resp);
				} else {
					req.setAttribute("errorMessage",
							"Registration succeeded, but leave balance initialization failed.");
					req.getRequestDispatcher("register.jsp").forward(req, resp);
				}
			} else {
				req.setAttribute("errorMessage", "Unable to register. Please try again.");
				req.getRequestDispatcher("register.jsp").forward(req, resp);
			}
		} catch (NumberFormatException e) {
			req.setAttribute("errorMessage", "Invalid department number format.");
			req.getRequestDispatcher("register.jsp").forward(req, resp);
		} catch (Exception e) {
			req.setAttribute("errorMessage", "An error occurred during registration. Please try again later.");
			req.getRequestDispatcher("register.jsp").forward(req, resp);
		}
	}
}