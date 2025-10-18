package com.aurionpro.app.common;

import java.time.Instant;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aurionpro.app.exception.StudentNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ApiError> handleNotFound(StudentNotFoundException ex, HttpServletRequest req) {
		ApiError body = new ApiError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage(),
				req.getRequestURI(), null);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiError> handleConflict(DataIntegrityViolationException ex, HttpServletRequest req) {
		ApiError body = new ApiError(Instant.now(), HttpStatus.CONFLICT.value(), "Conflict", ex.getMessage(),
				req.getRequestURI(), null);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(e -> e.getField() + ": " + e.getDefaultMessage()).toList();

		ApiError body = new ApiError(Instant.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request", "Validation failed",
				req.getRequestURI(), errors);
		return ResponseEntity.badRequest().body(body);
	}
}
