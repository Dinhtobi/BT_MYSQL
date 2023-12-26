package com.assignment.java.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResponse {

	private String notify ;
	
	private String message;
}
