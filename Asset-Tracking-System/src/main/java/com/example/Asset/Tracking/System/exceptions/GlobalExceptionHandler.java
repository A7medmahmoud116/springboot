package com.example.Asset.Tracking.System.exceptions;

import com.example.Asset.Tracking.System.response.ExeptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ExeptionResponse> handleResourceNotFound(ResourceNotFound exception) {
        ExeptionResponse error = new ExeptionResponse();
        error.setMessage(exception.getMessage());
        error.setStatus(NOT_FOUND.value());
        return new ResponseEntity<>(error,NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ExeptionResponse> handleAlreadyExistException(AlreadyExistException exception) {
        ExeptionResponse error = new ExeptionResponse();
        error.setMessage(exception.getMessage());
        error.setStatus(NOT_FOUND.value());
        return new ResponseEntity<>(error,NOT_FOUND);
    }

}
