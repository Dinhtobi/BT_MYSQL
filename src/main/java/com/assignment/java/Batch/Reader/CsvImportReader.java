package com.assignment.java.Batch.Reader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

import com.assignment.java.DTO.Payload.Request.CsvRequest;
import com.assignment.java.Entities.Product;
import com.assignment.java.Mapper.CsvProductMapper;

public class CsvImportReader implements ItemReader<CsvRequest> {

	private int index =1;
	
	@Override
	public CsvRequest read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		FlatFileItemReader<CsvRequest> itemReader = new FlatFileItemReader<CsvRequest>();
		itemReader.setLinesToSkip(index);
		itemReader.setResource(new FileSystemResource("G:\\Job\\AssignmentFinalJava\\upload\\InputProduct.csv"));
		DefaultLineMapper<CsvRequest> lineMapper = new DefaultLineMapper<CsvRequest>();
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer(";"));
		lineMapper.setFieldSetMapper(new CsvProductMapper());
		itemReader.setLineMapper(lineMapper);
		itemReader.open(new ExecutionContext());
		CsvRequest csvRequest = itemReader.read();
		
		if(csvRequest== null) {
			return null;
		} 
		System.out.println("Reader" + csvRequest.getName());
		
		index++;
		return csvRequest; 
	}

	
}
