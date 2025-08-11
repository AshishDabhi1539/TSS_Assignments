package com.tss.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String name = (String) session.getAttribute("userName");
        String theme = (String) session.getAttribute("theme");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<body style='background-color:" + theme + "'>");
        out.println("<h1>Welcome, " + name + " (Customer)</h1>");
        out.println("<p>Greetings! Thank you for joining us as a valued customer.</p>");
        out.println("</body>");
    }
}