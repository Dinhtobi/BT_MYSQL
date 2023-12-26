package com.assignment.java.Batch.Writer;

import java.util.Date;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import com.assignment.java.Entities.Product;
import com.assignment.java.Repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsvImportWriter implements ItemWriter<Product> {

	private final ProductRepository productRepository;
	
	
	public CsvImportWriter(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}


	@Override
	public void write(Chunk<? extends Product> chunk) throws Exception {

		for(Product product : chunk) {
			Product productSave = new Product();
			productSave.setName(product.getName());
			productSave.setId(product.getId());
			productSave.setCategory(product.getCategory());
			productSave.setNumber(product.getNumber());
			productSave.setPrice(product.getPrice());
			productSave.setCreatedAt(new Date());
			productRepository.save(productSave);
			log.info("Save product with name: {}" , product.getName());
		}
		
	}

	
}
