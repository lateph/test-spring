package com.mgg.springboot.services.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.mgg.springboot.beans.UploadFileInfo;
import com.mgg.springboot.controllers.UploadFilesController;
import com.mgg.springboot.services.UploadFilesService;

@Service
public class UploadFilesServiceImpl implements UploadFilesService {

  private final Path root = Paths.get("uploads");

  @Override
  public void init() {
    try {
      Files.createDirectory(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public UploadFileInfo save(MultipartFile file) {
    try {

      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));

      String filename = file.getOriginalFilename().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(UploadFilesController.class, "getFile", file.getOriginalFilename().toString()).build()
          .toString();

      System.out.println("url:" + url);

      return new UploadFileInfo(filename, url);

    } catch (Exception e) {

      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }

  @Override
  public Resource load(String filename) {
    try {
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  // we are not allowing this in controller
  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(root.toFile());
  }


  @Override
  public List<UploadFileInfo> loadAll() {
    try {
      Stream<Path> streamInfos = Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);

      List<UploadFileInfo> fileInfos = streamInfos.map(path -> {
        String filename = path.getFileName().toString();
        String url = MvcUriComponentsBuilder
            .fromMethodName(UploadFilesController.class, "getFile", path.getFileName().toString()).build().toString();
  
        return new UploadFileInfo(filename, url);
      }).collect(Collectors.toList());

      return fileInfos;

    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }

  @Override
  public UploadFileInfo saveBase64(String file) {
    // TODO Auto-generated method stub
    try
    {
        String[] strings = file.split(",");
        //This will decode the String which is encoded by using Base64 class
        byte[] imageByte=Base64.getDecoder().decode(strings[1]);

        String extension;
        switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default://should write cases for more images types
                extension = "jpg";
                break;
        }

        String filename = UUID.randomUUID()+"."+extension;
        String directory=this.root.resolve(filename).toString();
        new FileOutputStream(directory).write(imageByte);
        String url = MvcUriComponentsBuilder
          .fromMethodName(UploadFilesController.class, "getFile", filename).build()
          .toString();

        return new UploadFileInfo(directory, url);
    }
    catch(Exception e)
    {
      return null;
    }

  }

}
