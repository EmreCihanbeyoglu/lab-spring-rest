package com.cydeo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionWrapper> alreadyExistExceptionHandler(AlreadyExistsException exception, HttpServletRequest request) {

        // to be able to see the exceptions in the console additionally
        exception.printStackTrace();

        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(HttpStatus.CONFLICT.value(), exception.getMessage(), request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionWrapper);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionWrapper> notFoundExceptionHandler(NotFoundException exception, HttpServletRequest request) {

        // to be able to see the exceptions in the console additionally
        exception.printStackTrace();

        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionWrapper);


    }

    // DTO class fields validation like username, birthday, email, phone number etc
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionWrapper> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception, HttpServletRequest request) {

        // to be able to see the exceptions in the console additionally
        exception.printStackTrace();

        String message = "invalid input(s)";
        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(), message, request.getRequestURI());


        List<ValidationException> validationExceptions = new ArrayList<>();

        for (ObjectError error : exception.getBindingResult().getAllErrors()) {

            String errorField = ((FieldError) error).getField();
            Object rejectedValue = ((FieldError) error).getRejectedValue();
            String reason = error.getDefaultMessage();

            ValidationException validationException = new ValidationException(errorField, rejectedValue, reason);
            validationExceptions.add(validationException);
        }

        exceptionWrapper.setValidationExceptionList(validationExceptions);
        exceptionWrapper.setErrorCount(validationExceptions.size());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionWrapper);
    }

}
