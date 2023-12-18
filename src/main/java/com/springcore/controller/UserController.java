package com.springcore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcore.entities.User;
import com.springcore.service.IUserService;

import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	@Autowired
	private IUserService userService; 
	
	@PostMapping("/add")
	public ResponseEntity<?> insertUser(@RequestBody User user){
		User usersaved = userService.AddUser(user);
		return ResponseEntity.ok(usersaved);
	}
	
	@GetMapping("/distince")
	public ResponseEntity<?> GetUserDistince(@Param("email") String email){
		List<User> users = userService.getDistince(email);
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/email-username")
	public ResponseEntity<?> GetUserEmailAndUsername(@Param("email") String email , @Param("username") String username){
		return ResponseEntity.ok(userService.getUserByEmailAndUsername(email, username));
	}
	
	@GetMapping("/age")
	public ResponseEntity<?> GetUserByAge(@Param("from") int from , @Param("to") int to){
		return ResponseEntity.ok(userService.getUserByAge(from, to));
	}
	
	@GetMapping("/like")
	public ResponseEntity<?> GetUserLike(@Param("email") String email){
		return ResponseEntity.ok(userService.getUserLike(email));
	}
	
	@GetMapping("/like/start")
	public ResponseEntity<?> GetUserStartingWith(@Param("email") String email){
		return ResponseEntity.ok(userService.findByEmailStartingWith(email)); 
	}
	
	@GetMapping("/like/contain")
	public ResponseEntity<?> GetUserContain(@Param("email") String email){
		return ResponseEntity.ok(userService.findByEmailContaining(email));
	}
	
	@GetMapping("/orderby")
	public ResponseEntity<?> GetUserOrderByUsername(@Param("age") int age){
		return ResponseEntity.ok(userService.findByAgeOrderByUsernameDesc(age));
	}
	
	@GetMapping("/In")
	public ResponseEntity<?> GetUserByAgeIn(@Param("age1") int age1,@Param("age2") int age2,@Param("age3") int age3){
		return ResponseEntity.ok(userService.findByAgeIn(age1,age2,age3));
	}
	
	@GetMapping("/Ignorecase")
	public ResponseEntity<?> GetUserIgnorecase(@Param ("email") String email){
		return ResponseEntity.ok(userService.findByEmailIgnoreCase(email));
	}
	
	@GetMapping("/like/end")
	public ResponseEntity<?> GetUserEndingWith(@Param("email") String email){
		return ResponseEntity.ok(userService.findByEmailEndsWith(email));
	}
	
	@GetMapping("/native")
	public ResponseEntity<?> GetUserNative(@Param("email") String email){
		return ResponseEntity.ok(userService.findByEmailNative(email));
	}
	
	@GetMapping("/page/{offset}/{pagesize}/{feild}")
	public ResponseEntity<?> GetUserPage(@Param("id") int id , @PathVariable("offset") int offset 
			, @PathVariable("pagesize") int pagesize, @PathVariable("feild") String feild){
		return ResponseEntity.ok(userService.findUserPage(id,offset ,pagesize,feild));
	}
	
	@GetMapping("/projection")
	public ResponseEntity<?> Test(){
		userService.GetIdAndUsername();	
		return ResponseEntity.ok("test");
	}
}
