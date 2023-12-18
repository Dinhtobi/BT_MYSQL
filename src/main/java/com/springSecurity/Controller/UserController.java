package com.springSecurity.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springSecurity.Entities.User;
import com.springSecurity.Payload.Request.LoginRequest;
import com.springSecurity.Payload.Request.RefreshTokenRequest;
import com.springSecurity.Payload.Request.RegisterRequest;
import com.springSecurity.Payload.Response.UserResponse;
import com.springSecurity.Service.IRefreshTokenService;
import com.springSecurity.Service.IUserService;
import com.springSecurity.utils.JwtUtil;

import io.jsonwebtoken.security.Keys;
import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private IUserService userService;
	
	
	@Autowired
	private IRefreshTokenService refreshTokenService;

	@GetMapping("/{userId}")
	public ResponseEntity<?> getUser(@PathVariable("userId") int userId){
		return ResponseEntity.ok(userService.getUserById(userId));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		System.out.print("login");
		UserResponse user = userService.getUserByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
		return ResponseEntity.ok(user);
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
		return ResponseEntity.ok(userService.Register(registerRequest));
	}
	
	@PostMapping("/refreshToken")
	public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
		return ResponseEntity.ok(refreshTokenService.getRefreshTokenByToken(refreshTokenRequest.getRefreshToken()));
	}
}
