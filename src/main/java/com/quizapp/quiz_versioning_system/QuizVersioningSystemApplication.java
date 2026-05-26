package com.quizapp.quiz_versioning_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class QuizVersioningSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizVersioningSystemApplication.class, args);
	}

}
