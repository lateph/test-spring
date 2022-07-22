package com.mgg.springboot.beans;

import lombok.Data;

@Data
public class JwtErrorResponses {
  private String name;
  private String message;
  private int code;
  private String className;

  public JwtErrorResponses(String name, String message, int code, String className) {
    this.name = name;
    this.message = message;
    this.code = code;
    this.className = className;
  }
}
