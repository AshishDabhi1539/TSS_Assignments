package com.tss.jpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tss.jpa.error.ResponseError;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ResponseError> handleStudentException(StudentApiException exception) {
		ResponseError error = new ResponseError();
		error.setMessage(exception.getMessage());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setTimestamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

