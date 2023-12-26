package com.assignment.java.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.assignment.java.Entities.User;
import com.assignment.java.Exception.NotFoundException;
import com.assignment.java.Repository.UserRepository;
import com.assignment.java.Security.UserPrincipal;
import com.assignment.java.Service.IUserDeatailService;

@Service
public class UserDetailService implements IUserDeatailService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByEmail(String email) {

		User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
		if(user != null)
			return new UserPrincipal().create(user);
		else return null ;
	}

}
