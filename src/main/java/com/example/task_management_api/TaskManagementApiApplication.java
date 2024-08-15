package com.example.task_management_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.task_management_api")
public class TaskManagementApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(TaskManagementApiApplication.class, args);
	}

}
