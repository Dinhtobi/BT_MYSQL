package com.assignment.java.DTO.Payload.Response;

import com.assignment.java.DTO.Payload.Request.CsvRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CsvResponse  extends CsvRequest{
	
	
	public CsvResponse(int id, String name, int price, int number, String category) {
		super(id, name, price, number, category);
	}

}
