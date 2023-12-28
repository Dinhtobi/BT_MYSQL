package com.assignment.java.DTO.Payload.Request;

import lombok.Data;

@Data
public class RegisterRequest {
	
	private String email ;
	
	private String name;
	
	private String password; 
	
	private String role;
}
