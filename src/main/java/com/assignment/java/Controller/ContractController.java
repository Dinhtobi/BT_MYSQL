package com.assignment.java.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.java.DTO.Payload.Request.PostRequest;
import com.assignment.java.Service.IContractPostService;
import com.assignment.java.Service.IContractService;

@RestController
@RequestMapping("/contract")
public class ContractController {

	@Autowired
	private IContractService contractService;
	
	@Autowired 
	private IContractPostService contractPostService;
	
	@PostMapping
	public ResponseEntity<?> deployContract(@RequestParam("message") String message) throws Exception {
			 return ResponseEntity.ok(contractService.deploy(message));
	}
	
	@GetMapping
	public ResponseEntity<?> loadContract(@RequestParam("address") String address) throws Exception {
		return ResponseEntity.ok(contractService.load(address));
	}
	
	
	@PostMapping("/post")
	public ResponseEntity<?> addPostContract(@RequestBody PostRequest request ) throws Exception{
		return ResponseEntity.ok(contractPostService.addPost(request.getTitle(), request.getDescription(),request.getAddress()));
	}
	
	@GetMapping("/post")
	public ResponseEntity<?> getPostsContract(@RequestParam("address") String address ) throws Exception{
		return ResponseEntity.ok(contractPostService.getPosts(address));
	}
	
	@GetMapping("/post/deploy")
	public ResponseEntity<?> deployContract() throws Exception{
		return ResponseEntity.ok(contractPostService.deployPost());
	}
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<?> showPostContract(@PathVariable("postId") int postId, @RequestParam("address") String address) throws Exception{
		return ResponseEntity.ok(contractPostService.getPost(postId, address));
	}
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<?> showPostContract(@PathVariable("postId") int postId,@RequestBody PostRequest request) throws Exception{
		return ResponseEntity.ok(contractPostService.updatePost(postId, request.getTitle(), request.getDescription(), request.getAddress()));
	}
	
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<?> delPostContract(@PathVariable("postId") int postId, @RequestParam("address") String address) throws Exception{
		return ResponseEntity.ok(contractPostService.deletePost(postId, address));
	}
	
	@PatchMapping("/post/{postId}")
	public ResponseEntity<?> reStoragePostContract(@PathVariable("postId") int postId, @RequestParam("address") String address, @RequestParam("author") String author) throws Exception{
		return ResponseEntity.ok(contractPostService.RestorPost(postId, address,author));
	}
 }
