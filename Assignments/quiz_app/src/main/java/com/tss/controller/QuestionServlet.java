package com.tss.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.model.Question;
import com.tss.service.QuizService;

@WebServlet("/question")
public class QuestionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private QuizService quizService;

    public QuestionServlet() {
        super();
        this.quizService = new QuizService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        Integer currentQuestion = (Integer) session.getAttribute("currentQuestion");
        if (currentQuestion == null) {
            currentQuestion = 1;
            session.setAttribute("currentQuestion", currentQuestion);
        }

        try {
            if (quizService.hasNextQuestion(session)) {
                Question question = quizService.getQuestionById(currentQuestion);
                if (question == null) {
                    throw new SQLException("Question not found for ID: " + currentQuestion);
                }
                request.setAttribute("currentQuestion", currentQuestion);
                request.setAttribute("question", question);
                request.getRequestDispatcher("question.jsp").forward(request, response);
            } else {
                response.sendRedirect("score");
            }
        } catch (SQLException e) {
            response.setContentType("text/html");
            response.getWriter().println("<h3>Error loading question: " + e.getMessage() + "</h3>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        try {
            int questionId = Integer.parseInt(request.getParameter("questionId"));
            int selectedOption = Integer.parseInt(request.getParameter("answer"));
            if (selectedOption < 1 || selectedOption > 4) {
                request.setAttribute("error", "Invalid option selected");
                doGet(request, response);
                return;
            }
            quizService.processAnswer(session, questionId, selectedOption);
            response.sendRedirect("question");
        } catch (SQLException e) {
            response.setContentType("text/html");
            response.getWriter().println("<h3>Error processing answer: " + e.getMessage() + "</h3>");
        }
    }
}
