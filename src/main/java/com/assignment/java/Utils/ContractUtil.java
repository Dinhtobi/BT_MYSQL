package com.assignment.java.Utils;

import java.net.ConnectException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.WebSocketService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ContractUtil {
	@Value("${blockChain.url}")
	private String url ;
	
	@Value("${blockChain.ws}")
	private String wsLink ;
	
	@Value("${blockChain.privateKey}")
	private String key;
	
	private Web3j web3j;
	
	private Credentials credentials;
	
	private WebSocketService ws ; 
	@Bean
	public void Run() throws ConnectException {
		this.web3j = Web3j.build(new HttpService(url));
		this.credentials = Credentials.create(key);
		this.ws = new WebSocketService(wsLink, true);
		ws.connect();
	}
	
	public Web3j web3j() {
		return this.web3j; 
	}
	
	public Credentials credentials() {
		return this.credentials;
	}
	
	public Web3j webSocketService() throws ConnectException {
			Web3j web3j = Web3j.build(this.ws);
			return web3j;
	}
}
