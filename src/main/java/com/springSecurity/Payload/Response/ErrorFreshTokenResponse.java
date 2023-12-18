package com.springSecurity.Payload.Response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ErrorFreshTokenResponse {
	
	private int status;
    private Date timestamp;
    private String message;
    private String details;
}
