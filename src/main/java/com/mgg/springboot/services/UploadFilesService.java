package com.mgg.springboot.services;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.mgg.springboot.beans.UploadFileInfo;

public interface UploadFilesService {
  public void init();

  public UploadFileInfo save(MultipartFile file);
  public UploadFileInfo saveBase64(String file);

  public Resource load(String filename);

  public void deleteAll();

  public List<UploadFileInfo> loadAll();
}
