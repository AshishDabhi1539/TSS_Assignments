package com.tss.banking.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tss.banking.dto.response.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BankApiException.class)
    public ResponseEntity<ErrorResponseDto> handleBankApiException(BankApiException ex, WebRequest request) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            ex.getMessage(), 
            "Bank API Error", 
            HttpStatus.BAD_REQUEST.value(), 
            request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> validationErrors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            validationErrors.add(fieldName + ": " + errorMessage);
        });

        ErrorResponseDto errorResponse = new ErrorResponseDto(
            "Validation failed", 
            "Validation Error", 
            HttpStatus.BAD_REQUEST.value(), 
            request.getDescription(false).replace("uri=", "")
        );
        errorResponse.setValidationErrors(validationErrors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            "Invalid email or password", 
            "Authentication Error", 
            HttpStatus.UNAUTHORIZED.value(), 
            request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception ex, WebRequest request) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            "An internal server error occurred", 
            "Internal Server Error", 
            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}