package com.assignment.java.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DetailProductResponse {

	private String name ;
	
	private int price;
	
	private int number;
	
	private String category;
}
