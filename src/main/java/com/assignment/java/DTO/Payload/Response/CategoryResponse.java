package com.assignment.java.DTO.Payload.Response;

import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class CategoryResponse {

	private int id;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private String name ;
	
	public CategoryResponse(String name ,int id, Date createdAt) {
		this.id = id;
		this.createdAt = createdAt;
		this.name = name;
	}

	public CategoryResponse(String name ,int id, Date createdAt, Date updatedAt) {
		this.id = id;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
