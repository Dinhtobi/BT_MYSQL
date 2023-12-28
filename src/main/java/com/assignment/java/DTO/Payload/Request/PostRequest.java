package com.assignment.java.DTO.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PostRequest {
	private String title;
	
	private String description;
	
	private String address;
}
