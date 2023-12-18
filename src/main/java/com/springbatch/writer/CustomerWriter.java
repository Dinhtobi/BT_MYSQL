package com.springbatch.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class CustomerWriter implements ItemWriter<String> {

	@Override
	public void write(Chunk<? extends String> chunk) throws Exception {

		for(String data : chunk) {
			System.out.println("Writer data" + data );
		}
		System.out.println("Writer completed");
		
	}

	
}
