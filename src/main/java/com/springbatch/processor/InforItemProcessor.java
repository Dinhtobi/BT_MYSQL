package com.springbatch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.springbatch.model.Information;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InforItemProcessor implements ItemProcessor<Information, Information>{

	@Override
	public Information process(Information item) throws Exception {
	
		String name = item.getName();
		int age = item.getAge();
		double avgMark = item.getAvgMark();
		String Classification = "Excellent";
		if(avgMark < 0 || avgMark > 10 ) {
			Classification = "Error, Please check again!";
		}else if(avgMark < 6) Classification = "Failing";
		else if (avgMark<7) Classification = "Bellow average";
		else if (avgMark < 8) Classification = "Average";
		else if(avgMark < 9) Classification = "Good";
		Information information = new Information(name,age,avgMark,Classification);
		log.info("Convert {} to {}" , item , information);
		return information;
	}
	

}
