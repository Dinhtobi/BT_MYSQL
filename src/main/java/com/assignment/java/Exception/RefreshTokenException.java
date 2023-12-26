package com.assignment.java.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class RefreshTokenException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public RefreshTokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public RefreshTokenException(String message) {
		super(message);
	}
}
