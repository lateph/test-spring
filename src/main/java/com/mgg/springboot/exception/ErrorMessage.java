package com.mgg.springboot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private int code;
    private Date timestamp;
    private String message;
    private String description;
}