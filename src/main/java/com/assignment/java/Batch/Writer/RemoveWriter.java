package com.assignment.java.Batch.Writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import com.assignment.java.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RemoveWriter implements ItemWriter<Integer> {

	private final UserRepository userRepository;
	
	public RemoveWriter(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}



	@Override
	public void write(Chunk<? extends Integer> chunk) throws Exception {
		for(Integer user : chunk) {
			userRepository.deleteUser(user);
			log.info("User with id= {} deleted", user);
		}
	}

	
}
