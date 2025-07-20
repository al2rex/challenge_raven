package com.challengeraven.calculator.app.exception;

import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.challengeraven.calculator.app.config.Constants;
import com.challengeraven.calculator.app.dto.ApiErrorDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)

    public ResponseEntity<ApiErrorDTO> handleIllegalArgument(IllegalArgumentException ex) {
        ApiErrorDTO error = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                Constants.MSG_ERR_OPERATION,
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<ApiErrorDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String detail = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ApiErrorDTO apiError = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                Constants.MSG_ERR_VALIDATION,
                detail
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)

    public ResponseEntity<ApiErrorDTO> handleConstraintViolation(ConstraintViolationException ex) {
        String detail = ex.getConstraintViolations()
                .stream()
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .collect(Collectors.joining("; "));

        ApiErrorDTO apiError = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                Constants.MSG_ERR_PARAMETER,
                detail
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(Exception.class)

    public ResponseEntity<ApiErrorDTO> handleAll(Exception ex, HttpServletRequest request) throws Exception {
        String uri = request.getRequestURI();

        if (uri.startsWith("/swagger-ui") || uri.startsWith("/v3/api-docs") || uri.startsWith("/webjars")) {
            throw ex;
        }

        ApiErrorDTO error = new ApiErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno del servidor",
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorDTO> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String detailMessage = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
        String userMessage;

        if (detailMessage.contains("users_username_key")) {
            userMessage = Constants.MSG_ERR_USER;
        } else if (detailMessage.contains("users_email_key")) {
            userMessage = Constants.MSG_ERR_EMAIL;
        } else {
            userMessage = Constants.MSG_ERR_INTEGRITY;
        }

        ApiErrorDTO apiError = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                userMessage,
                detailMessage
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
}