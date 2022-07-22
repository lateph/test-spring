package com.mgg.springboot.exception;

import java.util.Map;

public class ValidationException extends RuntimeException{
    public Map<String, String> errors ;
    public ValidationException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }
}