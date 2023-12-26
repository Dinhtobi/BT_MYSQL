package com.assignment.java.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.java.Service.IContractService;




@RestController
@RequestMapping("/contract")
public class ContractController {

	@Autowired
	private IContractService contractService;
	
	@PostMapping
	public ResponseEntity<?> deployContract(@RequestParam("message") String message) throws Exception {
			 return ResponseEntity.ok(contractService.deploy(message));
	}
	
	@GetMapping
	public ResponseEntity<?> loadContract(@RequestParam("address") String address) throws Exception {
		return ResponseEntity.ok(contractService.load(address));
	}
	
}
