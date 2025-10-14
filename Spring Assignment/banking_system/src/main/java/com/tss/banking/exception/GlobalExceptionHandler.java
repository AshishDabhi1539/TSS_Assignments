package com.tss.banking.exception;

<<<<<<< HEAD
=======
import java.time.LocalDateTime;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
<<<<<<< HEAD
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tss.banking.dto.response.ErrorResponseDto;

@ControllerAdvice
=======
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.tss.banking.dto.response.ErrorResponseDto;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
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

<<<<<<< HEAD
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception ex, WebRequest request) {
=======
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        List<String> validationErrors = new ArrayList<>();
        ex.getConstraintViolations().forEach(violation -> {
            validationErrors.add(violation.getPropertyPath() + ": " + violation.getMessage());
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

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDto> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, WebRequest request) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            "Required parameter '" + ex.getParameterName() + "' is missing", 
            "Missing Parameter Error", 
            HttpStatus.BAD_REQUEST.value(), 
            request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            "Invalid value for parameter '" + ex.getName() + "': " + ex.getValue(), 
            "Type Mismatch Error", 
            HttpStatus.BAD_REQUEST.value(), 
            request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ErrorResponseDto> handleDisabledException(DisabledException ex, WebRequest request) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            "Account is disabled. Please contact support.", 
            "Account Disabled", 
            HttpStatus.FORBIDDEN.value(), 
            request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ErrorResponseDto> handleLockedException(LockedException ex, WebRequest request) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            "Account is locked. Please contact support.", 
            "Account Locked", 
            HttpStatus.FORBIDDEN.value(), 
            request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleRuntimeException(RuntimeException ex, WebRequest request) {
        log.error("Runtime exception occurred: ", ex);
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            "An error occurred while processing your request", 
            "Runtime Error", 
            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception ex, WebRequest request) {
        log.error("Unexpected exception occurred: ", ex);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            "An internal server error occurred", 
            "Internal Server Error", 
            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            request.getDescription(false).replace("uri=", "")
        );
<<<<<<< HEAD
=======
        errorResponse.setTimestamp(LocalDateTime.now());
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}