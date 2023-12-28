package com.assignment.java.DTO.Payload.Response;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ErrorResponse {
	
	private boolean success;
    private Date timestamp;
    private String message;
    private String details;
    private HttpStatus status;
    
}
