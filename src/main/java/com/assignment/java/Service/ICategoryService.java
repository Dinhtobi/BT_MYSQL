package com.assignment.java.Service;

import java.util.Date;

import com.assignment.java.DTO.Payload.Request.CategoryRequest;
import com.assignment.java.DTO.Payload.Response.CategoryResponse;
import com.assignment.java.DTO.Payload.Response.DeleteResponse;
import com.assignment.java.DTO.Payload.Response.DetailCategoryResponse;
import com.assignment.java.DTO.Payload.Response.PageResponse;

public interface ICategoryService {

	CategoryResponse addCategory(CategoryRequest categoryRequest);
	
	CategoryResponse updateCategory(int id ,CategoryRequest categoryRequest);
	
	DeleteResponse deleteCategory(int id);
	
	PageResponse<CategoryResponse> filter(Date from, Date to, 
		 String searchField,String keyword, String sortType, String sortField, int size, int page);
	
	DetailCategoryResponse getCategoryById(int id);
}
