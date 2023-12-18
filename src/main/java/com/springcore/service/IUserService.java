package com.springcore.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;

import com.springcore.entities.User;

public interface IUserService {
	

	User AddUser(User user) ;
	
	List<User> getDistince(String email);
	
	User getUserByEmailAndUsername(String email, String username);
	
	List<User> getUserByAge(int from , int to);
	
	List<User> getUserLike(String like);
	
	List<User> findByEmailStartingWith(String email);
	
	List<User> findByEmailContaining(String email);
	
	List<User> findByAgeOrderByUsernameDesc(int age);
	
	List<User> findByAgeIn(int a , int b , int c);
	
	List<User> findByEmailIgnoreCase(String email);	
	
	List<User> findByEmailEndsWith(String endemail);
	
	List<User> findByEmailNative(String email);
	
	Page<User> findUserPage(int id ,int offset ,int pagesize, String field);
	
	void GetIdAndUsername();
	
}
