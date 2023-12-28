package com.assignment.java.Config;

import java.io.IOException;
import java.util.UUID;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;


@Configuration
@EnableScheduling
@Slf4j
public class ScheduledConfig {

	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("job-batch")
	Job processJob;
	
	@Autowired
	@Qualifier("job-blockChain")
	Job jobBlockChain;
	@Scheduled(fixedRate = 20000)
	public void runJob() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException  {
		log.info("Run job verify");
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("id", String.valueOf("Verification " + UUID.randomUUID())).toJobParameters();
					jobLauncher.run(processJob, jobParameters);
	}
	
	
	@Scheduled(fixedRate = 60*1000)
	public void listener() throws IOException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException   {
		log.info("Run contract verify");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("contracId", UUID.randomUUID().toString()).toJobParameters();
		jobLauncher.run(jobBlockChain, jobParameters);
					
	}
}
