package com.mgg.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mgg.springboot.beans.UploadFileInfo;
import com.mgg.springboot.beans.UploadResponse;
import com.mgg.springboot.services.UploadFilesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "uploads", description = "the Upload Files API")
@CrossOrigin
public class UploadFilesController {

  @Autowired
  UploadFilesService service;

   // ============= swagger =================================================
   @Operation(summary = "Upload Files", description = "Auto-generated description", tags = { "upload files" })
   @ApiResponses(value = {
           @ApiResponse(responseCode = "201", description = "Contact created"),

           @ApiResponse(responseCode = "400", description = "Invalid input"),

           @ApiResponse(responseCode = "409", description = "Contact already exists") })
   // ============= swagger =================================================

  @PostMapping("/uploads")
  public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {

    String message = "";

    try {
      UploadFileInfo fileInfo = service.save(file);

      return ResponseEntity.status(HttpStatus.OK).body(fileInfo);

    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new UploadResponse(message));

    }
  }

  @GetMapping("/uploads")
  public ResponseEntity<List<UploadFileInfo>> getListFiles() {

    List<UploadFileInfo> fileInfos = service.loadAll();

    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }

  @GetMapping("/uploads/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {

    Resource file = service.load(filename);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
}
