package com.example.basic_banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BasicBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicBankingApplication.class, args);
	}

}
