package com.springbatch.reader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.io.FileSystemResource;

import com.springbatch.model.Information;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InformationReader implements ItemReader<Information> {

	protected static class InformationFieldSetMapper implements FieldSetMapper<Information>{
		public Information mapFieldSet(FieldSet fieldSet) {
			Information information = new Information();
			information.setName(fieldSet.readString(0));
			information.setAge(fieldSet.readInt(1));
			information.setAvgMark(fieldSet.readDouble(2));
			return information;
		}
	}
	private int index =1;
	
	
	@Override
	public Information read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		FlatFileItemReader<Information> itemReader = new FlatFileItemReader<Information>();
		itemReader.setLinesToSkip(index);
		itemReader.setResource(new FileSystemResource("G:\\Job\\SpringBatch\\src\\main\\resources\\read.csv"));
		DefaultLineMapper<Information> lineMapper = new DefaultLineMapper<Information>();
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
		lineMapper.setFieldSetMapper(new InformationFieldSetMapper());
		itemReader.setLineMapper(lineMapper);
		itemReader.open(new ExecutionContext());
		Information information = itemReader.read();
		
		if(information== null) {
			return null;
		} 
		System.out.println("Reader" + information.getName());
		
		index++;
		return information; 
	}
	
	
	
	
}
