package com.assignment.java.Batch.Processor;

import org.springframework.batch.item.ItemProcessor;

import com.assignment.java.Entities.Product;
import com.assignment.java.Payload.Response.CsvResponse;

public class CsvExportProcessor implements ItemProcessor<Product, CsvResponse>{

	@Override
	public CsvResponse process(Product item) throws Exception {
		return new CsvResponse(item.getId(), item.getName(), item.getPrice(), item.getNumber(), item.getCategory().getName());
	}

}
