package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tss.database.DBConnection;
import com.tss.exception.AppException;
import com.tss.model.Feedback;

public class FeedbackDaoImpl implements FeedbackDao {
	private static final String INSERT_SQL = "INSERT INTO feedback "
			+ "(name, session_date, feedback_text, query_resolution, interactivity, impactful_learning, content_delivery) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

	@Override
	public int saveFeedback(Feedback fb) throws AppException {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pst = conn.prepareStatement(INSERT_SQL)) {

			pst.setString(1, fb.getName());
			pst.setDate(2, fb.getSessionDate());
			pst.setString(3, fb.getFeedbackText());
			pst.setInt(4, fb.getQueryResolution());
			pst.setInt(5, fb.getInteractivity());
			pst.setInt(6, fb.getImpactfulLearning());
			pst.setInt(7, fb.getContentDelivery());

			return pst.executeUpdate();
		} catch (SQLException e) {
			throw new AppException("Error saving feedback", e);
		}
	}
}
