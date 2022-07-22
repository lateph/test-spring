package com.mgg.springboot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessageModel {
    private int code;
    private Date timestamp;
    private String message;
    private String description;
    private Map<String, String> errors;
}