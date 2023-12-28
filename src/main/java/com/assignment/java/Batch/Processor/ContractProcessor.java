package com.assignment.java.Batch.Processor;

import org.springframework.batch.item.ItemProcessor;

import com.assignment.java.DTO.Model.PostDTO;

public class ContractProcessor implements ItemProcessor<PostDTO, PostDTO>{

	@Override
	public PostDTO process(PostDTO item) throws Exception {
		
		return item;
	}

	
}
