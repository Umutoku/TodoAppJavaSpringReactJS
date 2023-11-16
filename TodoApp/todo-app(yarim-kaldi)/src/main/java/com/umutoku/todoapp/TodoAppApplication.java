package com.umutoku.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"controller","service","model", "repository"})

public class TodoAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(TodoAppApplication.class, args);
	}

}
