package com.project.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.project.dao")
@ComponentScan("com.project")
public class SharingPlatformApplication {
	public static void main(String[] args) {
		SpringApplication.run(SharingPlatformApplication.class, args);
	}
}
