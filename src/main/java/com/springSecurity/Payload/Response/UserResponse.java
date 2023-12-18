package com.springSecurity.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
	
	private int id;
	
	private String username;
	
	private String token;
	
	private String refreshToken;
}
