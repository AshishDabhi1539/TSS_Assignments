package com.tss.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.dao.ResultDAO;
import com.tss.model.Result;
import com.tss.model.User;
import com.tss.service.QuizService;

@WebServlet("/score")
public class ScoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private QuizService quizService;
    private ResultDAO resultDAO;

    public ScoreServlet() {
        super();
        this.quizService = new QuizService();
        this.resultDAO = new ResultDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        try {
            int score = quizService.getFinalScore(session);
            User user = (User) session.getAttribute("user");
            Timestamp now = new Timestamp(System.currentTimeMillis());
            Result result = new Result(0, user.getId(), score, now, user.getUsername());
            resultDAO.saveResult(result);

            request.setAttribute("score", score);
            request.setAttribute("user", user);
            request.setAttribute("result", result);
            request.getRequestDispatcher("score.jsp").forward(request, response);

            // Optional: invalidate session after showing score
            // session.invalidate();
        } catch (SQLException e) {
            response.setContentType("text/html");
            response.getWriter().println("<h3>Error saving score: " + e.getMessage() + "</h3>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
