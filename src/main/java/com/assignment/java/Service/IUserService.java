package com.assignment.java.Service;

import java.io.UnsupportedEncodingException;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import com.assignment.java.DTO.Payload.Request.LoginRequest;
import com.assignment.java.DTO.Payload.Request.RegisterRequest;
import com.assignment.java.DTO.Payload.Response.RegisterResponse;
import com.assignment.java.DTO.Payload.Response.UserResponse;
import com.assignment.java.Entities.User;

import jakarta.mail.MessagingException;

public interface IUserService {

	UserResponse loadByEmailAndPassword(LoginRequest loginRequest);
	
	RegisterResponse register(RegisterRequest registerRequest) throws UnsupportedEncodingException, MessagingException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException;
	
	UserResponse verifyAccount(String code, int id) ;
}
