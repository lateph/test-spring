package com.mgg.springboot.beans;

import lombok.Data;

@Data
public class UploadFileInfo {
  private String name;
  private String url;

  public UploadFileInfo(String name, String url) {
    this.name = name;
    this.url = url;
  }
}