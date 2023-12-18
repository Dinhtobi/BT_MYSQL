package com.springSecurity.Advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.springSecurity.Exception.TokenRefreshException;
import com.springSecurity.Payload.Response.ErrorFreshTokenResponse;
@RestControllerAdvice
public class TokenControllerAdvice {

	@ExceptionHandler(value = TokenRefreshException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorFreshTokenResponse handleTokenRefreshException(TokenRefreshException e , WebRequest request) {
		return new ErrorFreshTokenResponse(HttpStatus.FORBIDDEN.value(), new Date(), e.getMessage(), request.getDescription(false));
	}
}
