package com.springbatch.processor;

import org.springframework.batch.item.ItemProcessor;

public class CustomProcessor implements ItemProcessor<String,String> {

	@Override
	public String process(String item) throws Exception {
		System.out.println("Processing Data" + item);
		item = item.toUpperCase();
		return item;
	}

}
