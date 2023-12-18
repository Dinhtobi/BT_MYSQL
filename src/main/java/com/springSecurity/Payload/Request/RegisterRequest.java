package com.springSecurity.Payload.Request;

import lombok.Data;

@Data
public class RegisterRequest {
	
	private String username ;
	
	private String password; 
	
	private String role;
}
