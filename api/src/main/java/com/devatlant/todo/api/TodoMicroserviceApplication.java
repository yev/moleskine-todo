package com.devatlant.todo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.devatlant.todo.business.entity")
@SpringBootApplication
public class TodoMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoMicroserviceApplication.class, args);
	}

}
