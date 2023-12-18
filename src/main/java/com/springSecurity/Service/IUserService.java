package com.springSecurity.Service;

import java.util.Optional;

import com.springSecurity.Entities.User;
import com.springSecurity.Payload.Request.RegisterRequest;
import com.springSecurity.Payload.Response.UserResponse;

public interface IUserService {
	UserResponse getUserByUsernameAndPassword(String username , String password);
	
	User getUserById(int id);
	
	UserResponse Register(RegisterRequest registerRequest);
}
