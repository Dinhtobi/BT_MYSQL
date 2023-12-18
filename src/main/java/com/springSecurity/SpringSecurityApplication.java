package com.springSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureAlgorithm;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		 
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
