package com.assignment.java.Batch.Reader;

import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.assignment.java.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RemoveReader implements ItemReader<Integer> {
	
	private int index = -1;

	private final UserRepository userRepository;
	
	public RemoveReader(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	
	@Override
	public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		index++;
		long miliis = System.currentTimeMillis() - 5*60000;
		log.info("mili {} date {} , datesend {}" , miliis , new Date(),new Date(System.currentTimeMillis() - 5*60000) );
		List<Integer> ids = userRepository.getIds(new Date(System.currentTimeMillis() - 5*60000));
		if(index >= ids.size() || ids == null) {
			log.info("ids {} index {}" ,ids.size() , index );
			index = -1;
			return null;
		}
		return ids.get(index);
	}
	
}
