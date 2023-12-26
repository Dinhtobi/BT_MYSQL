package com.assignment.java.Service;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserDeatailService {
	UserDetails loadUserByEmail(String email);
}
