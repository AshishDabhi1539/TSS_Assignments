package com.tss.dao;

import com.tss.exception.AppException;
import com.tss.model.Feedback;

public interface FeedbackDao {
	int saveFeedback(Feedback fb) throws AppException;
}