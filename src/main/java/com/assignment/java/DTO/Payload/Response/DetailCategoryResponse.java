package com.assignment.java.DTO.Payload.Response;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DetailCategoryResponse {
	
	private int id;
	
	private String name;
	
	private List<DetailProductResponse> products;
}
