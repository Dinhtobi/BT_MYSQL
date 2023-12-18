package com.springSecurity.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class TokenRefreshException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TokenRefreshException(String message, Throwable cause) {
		super(message, cause);
	}

	public TokenRefreshException(String message) {
		super(message);
	}
	
}
