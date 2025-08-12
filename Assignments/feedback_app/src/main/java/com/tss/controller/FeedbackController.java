package com.tss.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.exception.AppException;
import com.tss.model.Feedback;
import com.tss.service.FeedbackService;
import com.tss.service.FeedbackServiceImpl;

@WebServlet("/submitFeedback")
public class FeedbackController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final FeedbackService service = new FeedbackServiceImpl();

	public FeedbackController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String sessionDateStr = request.getParameter("session_date"); // yyyy-mm-dd
		String feedbackText = request.getParameter("feedback_text");
		String qRes = request.getParameter("query_resolution");
		String inter = request.getParameter("interactivity");
		String impact = request.getParameter("impactful_learning");
		String delivery = request.getParameter("content_delivery");

		try {
			Feedback fb = new Feedback();
			fb.setName(name);

			if (sessionDateStr != null && !sessionDateStr.isEmpty()) {
				fb.setSessionDate(Date.valueOf(sessionDateStr));
			} else {
				fb.setSessionDate(Date.valueOf(LocalDate.now()));
			}

			fb.setFeedbackText(feedbackText);
			fb.setQueryResolution(Integer.parseInt(qRes));
			fb.setInteractivity(Integer.parseInt(inter));
			fb.setImpactfulLearning(Integer.parseInt(impact));
			fb.setContentDelivery(Integer.parseInt(delivery));

			service.submitFeedback(fb);

			request.setAttribute("name", name);
			request.getRequestDispatcher("success.jsp").forward(request, response);

		} catch (AppException exception) {
			request.setAttribute("error", exception.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", "Invalid input: " + e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
