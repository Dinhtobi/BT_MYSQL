package com.assignment.java.Service.Impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.java.DTO.Payload.Request.LoginRequest;
import com.assignment.java.DTO.Payload.Request.RegisterRequest;
import com.assignment.java.DTO.Payload.Response.RegisterResponse;
import com.assignment.java.DTO.Payload.Response.UserResponse;
import com.assignment.java.Entities.Role;
import com.assignment.java.Entities.User;
import com.assignment.java.Exception.BadRequestException;
import com.assignment.java.Exception.NotFoundException;
import com.assignment.java.Repository.RoleRepository;
import com.assignment.java.Repository.UserRepository;
import com.assignment.java.Service.IRefreshTokenService;
import com.assignment.java.Service.IUserService;
import com.assignment.java.Utils.AppConstants;
import com.assignment.java.Utils.JwtUtil;

import jakarta.mail.MessagingException;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private IRefreshTokenService refreshTokenService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired 
	private EmailService emailService;
	
	
	@Override
	public RegisterResponse register(RegisterRequest registerRequest) throws UnsupportedEncodingException, MessagingException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		User user = new User();
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		Role role = roleRepository.findByName(registerRequest.getRole()).orElseThrow(() -> new NotFoundException("Role not found"));
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		user.setRoles(roles);
		user.setName(registerRequest.getName());
		user.setCreatedAt(new Date());
		user.setEnabled(false);
		user.setVerificationCode(UUID.randomUUID().toString().substring(0, 6));
		userRepository.save(user);
		User usersaved = userRepository.findByEmail(registerRequest.getEmail()).orElseThrow(() ->new NotFoundException("User not found"));
		emailService.sendVerificationEmail(usersaved.getId(), AppConstants.SITE_URL);
		return new RegisterResponse("Register Complete" , "Check your email, you need verify Account");
	}

	@Override
	public UserResponse loadByEmailAndPassword(LoginRequest loginRequest) {
		User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new NotFoundException("User not found"));
		if(passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())) {
			String token = jwtUtil.generateToken(user.getEmail());
			String refreshToken = refreshTokenService.createOrUpdateRefreshToken(user.getId());
			return new UserResponse(user.getId(), user.getName() ,user.getEmail(),token ,refreshToken );
		}else {
			return null;
		}
	}


	@Override
	public UserResponse verifyAccount(String code, int id)  {
		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
		
		if(user.getVerificationCode().equals(code)) {
			String token =  jwtUtil.generateToken(user.getEmail());
			String refreshToken = refreshTokenService.createOrUpdateRefreshToken(user.getId());
			user.setEnabled(true);
			user.setVerificationCode(null);
			userRepository.save(user);
			return new UserResponse(user.getId(), user.getName() ,user.getEmail(),token ,refreshToken );
		}else {
			throw new BadRequestException("Verify fail!");
		}
		
	}

	
	
}
