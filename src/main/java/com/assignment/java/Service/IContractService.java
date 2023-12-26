package com.assignment.java.Service;

public interface IContractService {
	String deploy(String message) throws Exception;
	String load(String address) throws Exception;
}
