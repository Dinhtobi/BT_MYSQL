package com.assignment.java.Service.Impl;

import java.math.BigInteger;
import java.util.List;

import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.tx.gas.StaticGasProvider;

import com.assignment.java.Contract.Message;
import com.assignment.java.Contract.Message.ActionEventResponse;
import com.assignment.java.Contract.Message.PostStruct;
import com.assignment.java.DTO.Payload.Response.DeployPostResponse;
import com.assignment.java.DTO.Payload.Response.NotifyPostContract;
import com.assignment.java.Service.IContractPostService;
import com.assignment.java.Utils.AppConstants;
import com.assignment.java.Utils.ContractUtil;

import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ContractPostService implements IContractPostService{

	@Autowired
	private ContractUtil contractUtil;
	
	
	@Override
	public DeployPostResponse deployPost() throws Exception {
		BigInteger gasPrice = BigInteger.valueOf(200_000L);
		BigInteger gasLimit = BigInteger.valueOf(8_000_000);
		StaticGasProvider gasProvider = new StaticGasProvider(gasPrice, gasLimit);
		Message message = Message.deploy(contractUtil.web3j(), contractUtil.credentials(), gasProvider).send();
		AppConstants.addressContract = message.getContractAddress();
		return new DeployPostResponse("Success", message.getContractAddress());
	}
		
	@Override
	public Message addPost(String type, String description, String address) throws Exception {
		BigInteger gasPrice = BigInteger.valueOf(200_000L);
		BigInteger gasLimit = BigInteger.valueOf(8_000_000);
		StaticGasProvider gasProvider = new StaticGasProvider(gasPrice, gasLimit);
		Message message = Message.load(address, contractUtil.web3j(), contractUtil.credentials(), gasProvider);
		message.createPost(type, description).send();
		return message;
	}

	@Override
	public NotifyPostContract updatePost(int postId, String title, String description, String address) throws Exception {
		BigInteger gasPrice = BigInteger.valueOf(200_000L);
		BigInteger gasLimit = BigInteger.valueOf(8_000_000);
		StaticGasProvider gasProvider = new StaticGasProvider(gasPrice, gasLimit);
		Message message = Message.load(address, contractUtil.web3j(), contractUtil.credentials(), gasProvider);
		message.updatePost(new BigInteger(String.valueOf(postId)), title, description).send();
		return new NotifyPostContract("Success" , "Update Complete");
	}	

	@Override
	public NotifyPostContract deletePost(int postId, String address) throws Exception {
		BigInteger gasPrice = BigInteger.valueOf(200_000L);
		BigInteger gasLimit = BigInteger.valueOf(8_000_000);
		StaticGasProvider gasProvider = new StaticGasProvider(gasPrice, gasLimit);
		Message message = Message.load(address, contractUtil.web3j(), contractUtil.credentials(), gasProvider);
		message.deletePost(new BigInteger(String.valueOf(postId))).send();
		return new NotifyPostContract("Success" , "Delete Complete");
	}

	@Override
	public PostStruct getPost(int postId, String address) throws Exception {
		BigInteger gasPrice = BigInteger.valueOf(200_000L);
		BigInteger gasLimit = BigInteger.valueOf(8_000_000);
		StaticGasProvider gasProvider = new StaticGasProvider(gasPrice, gasLimit);
		Message message = Message.load(address, contractUtil.web3j(), contractUtil.credentials(), gasProvider);
		
		return message.showPost(new BigInteger(String.valueOf(postId))).send();
	}

	@Override
	public List getPosts(String address) throws Exception {
		BigInteger gasPrice = BigInteger.valueOf(200_000L);
		BigInteger gasLimit = BigInteger.valueOf(8_000_000);
			StaticGasProvider gasProvider = new StaticGasProvider(gasPrice, gasLimit);
			Message message = Message.load(address, contractUtil.web3j(), contractUtil.credentials(), gasProvider);
			return message.getPosts().send();
	}

	@Override
	public NotifyPostContract RestorPost(int postId, String address, String author) throws Exception {
		BigInteger gasPrice = BigInteger.valueOf(200_000L);
		BigInteger gasLimit = BigInteger.valueOf(8_000_000);
		StaticGasProvider gasProvider = new StaticGasProvider(gasPrice, gasLimit);
		Message message = Message.load(address, contractUtil.web3j(), contractUtil.credentials(), gasProvider);
		message.restorDeletePost(new BigInteger(String.valueOf(postId)),author).send();
		return new NotifyPostContract("Success" , "Restore Complete");
	}

	

	
}
