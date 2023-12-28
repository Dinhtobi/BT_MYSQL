package com.assignment.java.DTO.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NotifyPostContract {

	private String notify ;
	
	private String message;
}
