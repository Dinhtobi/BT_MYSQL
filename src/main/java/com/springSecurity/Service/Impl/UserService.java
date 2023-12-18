package com.springSecurity.Service.Impl;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springSecurity.Entities.Role;
import com.springSecurity.Entities.User;
import com.springSecurity.Exception.NotFoundException;
import com.springSecurity.Payload.Request.RegisterRequest;
import com.springSecurity.Payload.Response.UserResponse;
import com.springSecurity.Repository.RoleRepository;
import com.springSecurity.Repository.UserRepository;
import com.springSecurity.Service.IRefreshTokenService;
import com.springSecurity.Service.IUserService;
import com.springSecurity.utils.JwtUtil;

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
	
	@Override
	public UserResponse getUserByUsernameAndPassword(String username, String password) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Khong tim thay user"));
		if(passwordEncoder.matches(password, user.getPassword())) {
			String refreshToken = refreshTokenService.createRefreshToken(user.getId());
			String token = jwtUtil.generateToken(user.getUsername());
			return new UserResponse(user.getId(), user.getUsername() , token , refreshToken);
		}
		else {
			return null ;
		}
		
	}

	@Override
	public User getUserById(int id) {
		return userRepository.findById(id).orElseThrow(()-> new NotFoundException("Khong tim thay user voi Id nay"));
	}

	@Override
	public UserResponse Register(RegisterRequest registerRequest) {
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		Set<Role> roles = new HashSet<Role>();
		Role role = roleRepository.findByName(registerRequest.getRole()).orElseThrow(() -> new NotFoundException("Role khong ton tai"));
		roles.add(role);
		user.setRoles(roles);
		userRepository.save(user);
		User usersaved = userRepository.findByUsername(registerRequest.getUsername()).orElseThrow(() -> new NotFoundException("Khong tim thay user"));
		String refreshToken = refreshTokenService.createRefreshToken(usersaved.getId());
		String token = jwtUtil.generateToken(user.getUsername());
		return new UserResponse(usersaved.getId(), usersaved.getUsername() , token, refreshToken);
	}


}
