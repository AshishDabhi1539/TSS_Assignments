package com.tss.service;

import com.tss.exception.AppException;
import com.tss.model.Feedback;

public interface FeedbackService {
	void submitFeedback(Feedback fb) throws AppException;
}