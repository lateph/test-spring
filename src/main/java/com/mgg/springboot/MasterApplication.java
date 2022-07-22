package com.mgg.springboot;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mgg.springboot.services.UploadFilesService;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
@Slf4j
public class MasterApplication implements CommandLineRunner {

	@Resource
	UploadFilesService storageService;

	public static void main(String[] args) {
		// log.info("Start the springboot template");
		SpringApplication.run(MasterApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		// storageService.deleteAll();
		// storageService.init();
	}

}
