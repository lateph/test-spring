package com.mgg.springboot.exception;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex) {
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(),
                                new Date(),
                                ex.getMessage(),
                                "Resource Not Found");
    }

    @ExceptionHandler(value = ClassNotFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage classNotFoundException(ClassNotFoundException ex) {
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                new Date(),
                                ex.getMessage(),
                                "Class Not Found On The Classpath");
    }

    @ExceptionHandler(value = InvocationTargetException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage invocationTargetException(InvocationTargetException ex) {
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                new Date(),
                                ex.getMessage(),
                                "Failed To Invoke Method or Constructor");
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
    public ErrorMessageModel constrainViolationException(ConstraintViolationException ex) {
        // List<String> errors = ex.getConstraintViolations().stream().map(s -> s.getMessage()).collect(Collectors.toList());
        Map<String, String> errors = ex.getConstraintViolations().stream().map(s -> s).collect(Collectors.toMap(s-> s.getPropertyPath().toString(), s-> s.getMessage()));
        ConstraintViolation<?> violation = ex.getConstraintViolations().iterator().next();
        // get the last node of the violation
        return new ErrorMessageModel(HttpStatus.NOT_IMPLEMENTED.value(),
                                new Date(),
                                violation.getMessage(),
                                "failed contol",
                                errors);
    }

    @ExceptionHandler(value = ValidationException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageModel validationException(ValidationException ex) {
        // get the last node of the violation
        return new ErrorMessageModel(HttpStatus.NOT_IMPLEMENTED.value(),
                                new Date(),
                                ex.getMessage(),
                                "failed contol",
                                ex.errors);
    }
}