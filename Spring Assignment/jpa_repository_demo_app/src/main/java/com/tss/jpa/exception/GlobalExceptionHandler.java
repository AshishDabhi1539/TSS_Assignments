package com.tss.jpa.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

	/*
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public
	 * ResponseEntity<ResponseError>
	 * handleValidationException(MethodArgumentNotValidException ex) { // Collect
	 * all field errors into a single string with each error on a new line String
	 * errorMessage = ex.getBindingResult().getFieldErrors().stream() .map(err ->
	 * err.getField() + ": " +
	 * err.getDefaultMessage()).collect(Collectors.joining(", "));
	 * 
	 * ResponseError error = new ResponseError();
	 * error.setMessage(errorMessage.isEmpty() ? "Validation failed" :
	 * errorMessage); error.setStatus(HttpStatus.BAD_REQUEST.value());
	 * error.setTimestamp(System.currentTimeMillis()); return new
	 * ResponseEntity<>(error, HttpStatus.BAD_REQUEST); }
	 */

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseError> handleValidationException(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<>();

		exception.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});

		ResponseError responseError = new ResponseError(HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis(),
				"Validation failed", errors);

		return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
	}

}