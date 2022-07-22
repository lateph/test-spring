package com.mgg.springboot.beans;

import lombok.Data;

@Data
public class UploadResponse {
  private String message;

  public UploadResponse(String message) {
    this.message = message;
  }
}
