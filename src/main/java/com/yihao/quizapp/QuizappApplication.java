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

	// run the ngrok to open the local port: ngrok tunnel --label edge=edghts_2ayP4SQqmIacQKNWPZn4efkW9CR http://localhost:8080
	// the link will be https://surely-grand-prawn.ngrok-free.app
	public static void main(String[] args) {
		SpringApplication.run(QuizappApplication.class, args);
	}

}
