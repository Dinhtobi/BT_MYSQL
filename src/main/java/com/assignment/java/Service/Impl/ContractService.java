package com.assignment.java.Service.Impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import com.assignment.java.Contract.HelloWorld;
import com.assignment.java.Service.IContractService;
import com.assignment.java.Utils.ContractUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ContractService implements IContractService{

	@Autowired
	private ContractUtil contractUtil;
	@Override
	public String deploy(String message) throws Exception {
		 BigInteger gasPrice = BigInteger.valueOf(20_000_000_000L); // Set your desired gas price
	        BigInteger gasLimit = BigInteger.valueOf(6_300_000); // Set your desired gas limit

//	         Create a custom gas provider with the specified gas limit
	    StaticGasProvider gasProvider = new StaticGasProvider(gasPrice, gasLimit);
		HelloWorld greeting = HelloWorld.deploy(contractUtil.web3j(), contractUtil.credentials(), gasProvider, message).send();
		log.info("address {}" , greeting.getContractAddress());
		BigInteger test = greeting.getGasPrice();	
		log.info("test " + test);
		String abc = greeting.message().send();
		log.info("abc" + abc);
		 return greeting.getContractAddress();
	}

	@Override
	public String load(String address) throws Exception {
		HelloWorld helloWorld =HelloWorld.load(address,contractUtil.web3j(), contractUtil.credentials(),new DefaultGasProvider());
		
			log.info("address {}" , helloWorld.getContractAddress());
			BigInteger test = helloWorld.getGasPrice();
			log.info("test " + test);
			String abc = helloWorld.message().send();
			log.info("abc " + abc);
			 
			 return helloWorld.getContractAddress();
	}

	
}
