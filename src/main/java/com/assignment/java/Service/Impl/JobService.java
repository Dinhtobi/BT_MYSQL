package com.assignment.java.Service.Impl;


import java.util.UUID;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.assignment.java.Service.IJobService;
@Service
public class JobService implements IJobService {

	
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	JobRepository jobRepository;
	
	
	
	@Autowired
	@Qualifier("job-import")
	Job csvImportJob;
	@Autowired
	@Qualifier("job-export")
	Job csvExportJob;
	
	
	
	@Override
	public void importFile(String url) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("id", String.valueOf(url)).toJobParameters();
					jobLauncher.run(csvImportJob, jobParameters);
	}

	@Override
	public void exportFile() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("id", String.valueOf(UUID.randomUUID().toString())).toJobParameters();
					jobLauncher.run(csvExportJob, jobParameters);
	}

	
}
