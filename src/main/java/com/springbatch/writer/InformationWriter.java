package com.springbatch.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.springbatch.Repository.InformationRepository;
import com.springbatch.model.Information;
public class InformationWriter implements ItemWriter<Information> {	

	private final InformationRepository informationRepository;

    @Autowired
    public InformationWriter(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

	@Override
	public void write(Chunk<? extends Information> chunk) throws Exception {

		for(Information information : chunk) {
			System.out.println("Save in Database");
			informationRepository.save(information);
		}
		System.out.println("Complete Writer");
		
	}

}
