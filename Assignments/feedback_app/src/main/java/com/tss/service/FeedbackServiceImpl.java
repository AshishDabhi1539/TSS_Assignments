package com.tss.service;

import com.tss.dao.FeedbackDao;
import com.tss.dao.FeedbackDaoImpl;
import com.tss.exception.AppException;
import com.tss.model.Feedback;

public class FeedbackServiceImpl implements FeedbackService {
	private final FeedbackDao dao = new FeedbackDaoImpl();

    @Override
    public void submitFeedback(Feedback fb) throws AppException {
        if (fb.getName() == null || fb.getName().trim().isEmpty()) {
            throw new AppException("Name is required");
        }
        if (fb.getSessionDate() == null) {
            throw new AppException("Session date is required");
        }

        int rows = dao.saveFeedback(fb);
        if (rows <= 0) {
            throw new AppException("Feedback not saved");
        }
    }
}
