package com.springSecurity.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

	
	@GetMapping("/page")
	public ResponseEntity<?> GetHomePage(){
		return ResponseEntity.ok("Home page");
	}
}
