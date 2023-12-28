package com.assignment.java.DTO.Payload.Response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
	private int id;

	private String email;

	private String name;
	
	private String token;

	private String refreshToken;
}
