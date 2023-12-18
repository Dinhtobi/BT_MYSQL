package com.springbatch.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Value;

public class CustomReader implements ItemReader<String> {

	private String[] tokens = {"Java" ,"Spring" , "Boot" ,"Spring" , "Batch"};
	
	private int index = 0;
	@Value("${file.sql}")
	public String filesql;
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if(index >= tokens.length) {
			return null;
		}
		String data = index + " " + tokens[index] ;
		index++;
		System.out.println("Reading data - " + data);
		return data;
	}

}
