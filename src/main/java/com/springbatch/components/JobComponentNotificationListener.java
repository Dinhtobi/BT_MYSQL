package com.springbatch.components;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.springbatch.model.Information;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JobComponentNotificationListener extends JobExecutionListenerSupport{
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JobComponentNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus()  == BatchStatus.COMPLETED) {
			
			log.info("Job Finished!");
			
			String query = "Select name , age , avg_mark , classification from information";
			jdbcTemplate.query(query,(rs, row) -> new Information(rs.getString(1) , rs.getInt(2), rs.getDouble(3) , rs.getString(4)))
						.forEach(infor -> log.info("Found information {} - {} - {} - {}" , infor.getName() , infor.getAge() , infor.getAvgMark(), infor.getClassfication() ));
		}
	}
	
	
}
