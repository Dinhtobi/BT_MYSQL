package com.springcore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import com.springcore.entities.User;
import com.springcore.repository.UserRepository;
import com.springcore.service.IUserService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class UserServiceImpl implements IUserService{
	
	@Override
	public List<User> findByAgeIn(int a, int b , int c) {
		List<Integer> ages = new ArrayList<>();
		ages.add(a);
		ages.add(b);
		ages.add(c);
		List<User> userAgeIn = userRepository.findByAgeIn(ages).toList();
		return userAgeIn;
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	public User AddUser(User user) {
		User usersave = user;
		Date curendate = new Date();
		user.setCreateat(curendate);
		userRepository.save(usersave);
		return usersave;
	}

	@Override
	public List<User> getDistince(String email) {
		List<User> users = userRepository.findDistinctByEmail(email);
		return users;
	}

	@Override
	public User getUserByEmailAndUsername(String email, String username) {
		User user = userRepository.findByEmailAndUsername(email, username);
		return user;
	}

	@Override
	public List<User> getUserByAge(int from, int to) {
		return userRepository.findByAgeBetween(from, to);

	}

	@Override
	public List<User> getUserLike(String like) {
		return userRepository.findByEmailNotLike(like);
	}

	@Override
	public List<User> findByEmailStartingWith(String email) {
		return userRepository.findByEmailStartingWith(email);
	}

	@Override
	public List<User> findByEmailContaining(String email) {
		return userRepository.findByEmailContaining(email).toList();
	}

	@Override
	public List<User> findByAgeOrderByUsernameDesc(int age) {
		return userRepository.findByAgeOrderByUsernameDesc(age).toList();
	}

	@Override
	public List<User> findByEmailIgnoreCase(String email) {
		return userRepository.findByEmailIgnoreCase(email).toList();
	}

	@Override
	public List<User> findByEmailEndsWith(String endemail) {
		return userRepository.findByEmailEndsWith(endemail);
	}

	@Override
	public List<User> findByEmailNative(String email) {
		return userRepository.findByEmailNative(email);
	}

	@Override
	public Page<User> findUserPage(int id, int offset , int pagesize, String field) {
		return userRepository.findUserById(id, PageRequest.of(offset,pagesize).withSort(Sort.by(field).descending()));
	}

	@Override
	public void GetIdAndUsername() {
		List<Object[]> objects = userRepository.getIdandUsername();
		objects.stream().forEach(o -> log.info("Id: {} , Username : {}" , o[0] , o[1]));
	}
	
	
}
