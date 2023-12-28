package com.assignment.java.DTO.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DeployPostResponse {
	
	private String notify ;
	
	private String address;
}
