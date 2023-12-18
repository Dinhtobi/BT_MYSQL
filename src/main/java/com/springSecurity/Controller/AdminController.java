package com.springSecurity.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

	
	@GetMapping("/page")
	public ResponseEntity<?> getAdminPage(){
		return ResponseEntity.ok("Admin Page");
	}
}