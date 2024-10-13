package com.cydeo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionWrapper> alreadyExistExceptionHandler(AlreadyExistsException exception, HttpServletRequest request) {

        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(HttpStatus.CONFLICT.value(), exception.getMessage(), request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionWrapper);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionWrapper> notFoundExceptionHandler(NotFoundException exception, HttpServletRequest request) {
        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionWrapper);


    }

}
