package com.diego.babycare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BabycareApplication {

	public static void main(String[] args) {

		SpringApplication.run(BabycareApplication.class, args);
	}

}
