package com.assignment.java.Service;

import java.util.List;

import org.web3j.protocol.core.RemoteFunctionCall;

import com.assignment.java.Contract.Message;
import com.assignment.java.Contract.Message.PostStruct;
import com.assignment.java.DTO.Payload.Response.DeployPostResponse;
import com.assignment.java.DTO.Payload.Response.NotifyPostContract;

public interface IContractPostService {
	
	DeployPostResponse deployPost() throws Exception;
	
	Message addPost(String type, String description, String address) throws Exception;
	
	NotifyPostContract updatePost(int postId,String title, String description, String address) throws Exception;
	
	NotifyPostContract deletePost(int postId, String address) throws Exception;
	
	PostStruct getPost(int postId, String address) throws Exception;
	
	List getPosts(String address) throws Exception;
	
	NotifyPostContract RestorPost(int postId, String address, String author) throws Exception;
}
