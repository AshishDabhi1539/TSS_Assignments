package com.tss.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.service.UserService;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String role = request.getParameter("role");
        String theme = request.getParameter("theme");

        HttpSession session = request.getSession();
        session.setAttribute("userName", username);
        session.setAttribute("theme", theme);
        session.setAttribute("role", role);

        if (userService.isUserExists(username)) {
            if (userService.validateCredentials(username, password)) {
                String dbRole = userService.getUserRole(username);
                if (dbRole != null && dbRole.equalsIgnoreCase(role)) {
                    if (role.equalsIgnoreCase("Admin")) {
                        response.sendRedirect("AdminController");
                    } else if (role.equalsIgnoreCase("Customer")) {
                        response.sendRedirect("CustomerController");
                    } else {
                        response.getWriter().println("<h3>Invalid role!</h3>");
                    }
                } else {
                    response.getWriter().println("<h3>Invalid credentials for " + role + " role!</h3>");
                }
            } else {
                response.getWriter().println("<h3>Invalid credentials for " + role + "!</h3>");
            }
        } else {
            if (userService.registerUser(username, password, role)) {
                if (role.equalsIgnoreCase("Admin")) {
                    response.sendRedirect("AdminController");
                } else if (role.equalsIgnoreCase("Customer")) {
                    response.sendRedirect("CustomerController");
                } else {
                    response.getWriter().println("<h3>Invalid role!</h3>");
                }
            } else {
                response.getWriter().println("<h3>Registration Failed!</h3>");
            }
        }
    }
}