package com.example.movie_theater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieTheaterApplication {

	public static void main(String[] args) {

		SpringApplication.run(MovieTheaterApplication.class, args);
		System.out.println("http://localhost:8080/");
	}

}
