package com.assignment.java.Batch.Processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.assignment.java.Service.IEmailService;
import com.assignment.java.Service.Impl.EmailService;


public class RemoveProcessor implements ItemProcessor<Integer, Integer>{

	private final EmailService emailService;
	
	
	
	public RemoveProcessor(EmailService emailService) {
		super();
		this.emailService = emailService;
	}



	@Override
	public Integer process(Integer item) throws Exception {
		emailService.sendRemoveEmail(item);
		return item;
	}


}
