package com.assignment.java.Config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.assignment.java.Batch.Processor.CsvExportProcessor;
import com.assignment.java.Batch.Processor.CsvImportProcessor;
import com.assignment.java.Batch.Processor.RemoveProcessor;
import com.assignment.java.Batch.Reader.CsvExportReader;
import com.assignment.java.Batch.Reader.CsvImportReader;
import com.assignment.java.Batch.Reader.RemoveReader;
import com.assignment.java.Batch.Writer.CsvImportWriter;
import com.assignment.java.Batch.Writer.RemoveWriter;
import com.assignment.java.Entities.Product;
import com.assignment.java.Payload.Request.CsvRequest;
import com.assignment.java.Payload.Response.CsvResponse;
import com.assignment.java.Repository.CategoryRepository;
import com.assignment.java.Repository.ProductRepository;
import com.assignment.java.Repository.UserRepository;
import com.assignment.java.Service.Impl.EmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class BatchConfig {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;

	@Bean(name = "job-batch")
	public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new JobBuilder("deleteData", jobRepository).start(step1(jobRepository, transactionManager))
				.preventRestart().build();
	}

	@Bean
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("step1", jobRepository).<Integer, Integer>chunk(1, transactionManager)
				.reader(new RemoveReader(userRepository)).processor(new RemoveProcessor(emailService)).writer(new RemoveWriter(userRepository))
				.build();
	}

	@Bean(name = "job-import")
	public Job jobcsv(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new JobBuilder("jobCsv4", jobRepository).start(stepImport(jobRepository, transactionManager))
				.preventRestart().build();
	}

	@Bean
	public Step stepImport(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("stepCsv", jobRepository).<CsvRequest, Product>chunk(5, transactionManager)
				.reader(new CsvImportReader()).processor(new CsvImportProcessor(categoryRepository))
				.writer(new CsvImportWriter(productRepository)).build();
	}

	@Bean
	public FlatFileItemWriter<CsvResponse> itemWriter(){
		BeanWrapperFieldExtractor<CsvResponse> fieldExtractor = new BeanWrapperFieldExtractor<CsvResponse>();
		fieldExtractor.setNames(new String[] {"id","name" , "price" , "number" ,"category"});
		fieldExtractor.afterPropertiesSet();
		
		DelimitedLineAggregator<CsvResponse> lineAggregator = new DelimitedLineAggregator<CsvResponse>();
		lineAggregator.setDelimiter(";");
		lineAggregator.setFieldExtractor(fieldExtractor);
		return new FlatFileItemWriterBuilder<CsvResponse>()
				.name("ExportProduct")
				.resource(new FileSystemResource("G:\\Job\\AssignmentFinalJava\\upload\\ExportProduct.csv"))
				.lineAggregator(lineAggregator)
				.headerCallback(writer -> writer.write("id;name;price;number;category"))
				.build();
	}	

	@Bean(name = "job-export")
	public Job jobExport(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new JobBuilder("jobExport2", jobRepository).start(stepExport(jobRepository, transactionManager))
				.preventRestart().build();
	}

	@Bean
	public Step stepExport(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("stepExport", jobRepository).<Product, CsvResponse>chunk(5, transactionManager)
				.reader(new CsvExportReader(dataSource)).processor(new CsvExportProcessor()).writer(itemWriter())
				.build();
	}
}
