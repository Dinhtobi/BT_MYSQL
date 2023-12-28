package com.assignment.java.Batch.Processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.assignment.java.DTO.Payload.Request.CsvRequest;
import com.assignment.java.Entities.Category;
import com.assignment.java.Entities.Product;
import com.assignment.java.Exception.NotFoundException;
import com.assignment.java.Mapper.ProductMapper;
import com.assignment.java.Repository.CategoryRepository;

public class CsvImportProcessor implements ItemProcessor<CsvRequest, Product>{

	private final CategoryRepository categoryRepository;
	
	public CsvImportProcessor(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}



	@Override
	public Product process(CsvRequest item) throws Exception {
		Product product = ProductMapper.mapper(item);
		Category category = categoryRepository.findByName(item.getCategory()).orElseThrow(() -> new NotFoundException("Category not found"));
		product.setCategory(category);
		return product;
	}
	

}
