package com.springSecurity.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.springSecurity.Config.UserPrincipal;
import com.springSecurity.Entities.User;
import com.springSecurity.Exception.NotFoundException;
import com.springSecurity.Repository.UserRepository;
import com.springSecurity.Service.IUserDetailService;

@Service
public class UserDetailService implements IUserDetailService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User Not Found"));
		if(user!= null) {
			return new UserPrincipal().create(user);
		}else return null ;
	}

}
