package com.yihao.quizapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizappApplication {

	// Three design layers: controller -> service -> model
	// controller receives request from a client
	// service performs the logic to fulfill the request
	// model contains data
	// Need to specify the database connection in the application.properties file
	public static void main(String[] args) {
		SpringApplication.run(QuizappApplication.class, args);
	}

}
