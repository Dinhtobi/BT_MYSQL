package com.assignment.java.Advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.assignment.java.Exception.BadRequestException;
import com.assignment.java.Exception.RefreshTokenException;
import com.assignment.java.Payload.Response.ErrorResponse;
@RestControllerAdvice
public class ControllerExceptionAdvice {

	@ExceptionHandler(value = RefreshTokenException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorResponse handleTokenRefreshException(RefreshTokenException e , WebRequest request) {
		return new ErrorResponse(false, new Date(), e.getMessage(), request.getDescription(false), HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleBadRequestException(BadRequestException e, WebRequest request) {
		return new ErrorResponse(false, new Date(),e.getMessage(), request.getDescription(false),HttpStatus.BAD_REQUEST);
	}
}
