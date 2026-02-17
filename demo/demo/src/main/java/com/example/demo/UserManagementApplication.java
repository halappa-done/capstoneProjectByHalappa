package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class UserManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(UserManagementApplication.class, args);
	}

}
