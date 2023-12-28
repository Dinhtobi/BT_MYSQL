package com.assignment.java.Controller;

import java.io.UnsupportedEncodingException;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobParametersNotFoundException;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.java.DTO.Payload.Request.LoginRequest;
import com.assignment.java.DTO.Payload.Request.RegisterRequest;
import com.assignment.java.Exception.BadRequestException;
import com.assignment.java.Service.IUserService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private IUserService userService;
	
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) throws UnsupportedEncodingException, MessagingException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
		return ResponseEntity.ok(userService.register(registerRequest));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		return ResponseEntity.ok(userService.loadByEmailAndPassword(loginRequest));
	}
	
	@GetMapping("/verify")
	public ResponseEntity<?> verify(@RequestParam("code") String code, @RequestParam("id") int id) throws BadRequestException, NoSuchJobException, JobParametersNotFoundException, JobRestartException, JobExecutionAlreadyRunningException, JobInstanceAlreadyCompleteException, UnexpectedJobExecutionException, JobParametersInvalidException, NoSuchJobExecutionException, JobExecutionNotRunningException{
		
		return ResponseEntity.ok(userService.verifyAccount(code, id));
	}
	
}
