package com.example.bookclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class BookclubApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookclubApplication.class, args);
	}

}
