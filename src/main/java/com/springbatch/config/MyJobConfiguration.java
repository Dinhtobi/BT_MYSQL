package com.springbatch.config;


import java.io.File;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.batch.item.database.HibernateCursorItemReader;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.HibernateCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import com.springbatch.Repository.InformationRepository;
import com.springbatch.components.JobComponentNotificationListener;
import com.springbatch.model.Information;
import com.springbatch.model.Trade;
import com.springbatch.processor.InforItemProcessor;
import com.springbatch.processor.TradeProcessor;
import com.springbatch.reader.InformationReader;
import com.springbatch.utils.InformationRowMapper;
import com.springbatch.writer.InformationWriter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@Configuration
//@EnableBatchProcessing(dataSourceRef = "batchDataSource" , transactionManagerRef = "batchTransactionManager")
//@EnableBatchProcessing
public class MyJobConfiguration {

	@Autowired
	private InformationRepository informationRepository;
	
	
	// write txt
	@Bean
	public FlatFileItemWriter<Information> itemWriter1(){
		return new FlatFileItemWriterBuilder<Information>()
				.name("itemwriter")
				.resource(new FileSystemResource("G:\\Job\\SpringBatch\\src\\main\\resources\\test.txt"))
				.lineAggregator(new PassThroughLineAggregator<Information>())
				.build();
	}
	
	// write csv
	@Bean
	public FlatFileItemWriter<Information> itemWriter2(){
		BeanWrapperFieldExtractor<Information> fieldExtractor = new BeanWrapperFieldExtractor<Information>();
		fieldExtractor.setNames(new String[] {"name" , "age" , "avgMark" ,"Classfication"});
		fieldExtractor.afterPropertiesSet();
		
		DelimitedLineAggregator<Information> lineAggregator = new DelimitedLineAggregator<Information>();
		lineAggregator.setDelimiter(";");
		lineAggregator.setFieldExtractor(fieldExtractor);
		
//		FormatterLineAggregator<Information> lineAggregator = new FormatterLineAggregator<>();
//		lineAggregator.setFormat("%-10s%-2d%-2.01f%-10s");
//		lineAggregator.setFieldExtractor(fieldExtractor);
		
		return new FlatFileItemWriterBuilder<Information>()
				.name("InformationWriter")
				.resource(new FileSystemResource("G:\\Job\\SpringBatch\\src\\main\\resources\\writer.csv"))
				.lineAggregator(lineAggregator)
				.build();
	}
	
	//write csv2 
	@Bean
	public FlatFileItemWriter<Information> itemWriter3(){
		return new FlatFileItemWriterBuilder<Information>()
				.name("InformationWriter3")
				.resource(new FileSystemResource("G:\\Job\\SpringBatch\\src\\main\\resources\\writer.csv"))
				.delimited()
				.delimiter("|")
				.names(new String[] {"name" , "age" , "avgMark" , "classfication"})
				.build();
	}
	
	
	// writer json
		@Bean 
		public JsonFileItemWriter<Trade> itemWriterJson(){
			return new JsonFileItemWriterBuilder<Trade>()
					.jsonObjectMarshaller(new JacksonJsonObjectMarshaller<Trade>())
					.resource(new FileSystemResource("G:\\Job\\SpringBatch\\src\\main\\resources\\write2.json"))
					.name("writerJson")
					.build();
		}
		
		@Bean
		public JdbcBatchItemWriter<Information> jdbcWriter(DataSource dataSource) {
		    return new JdbcBatchItemWriterBuilder<Information>()
		            .dataSource(dataSource)
		            .sql("INSERT INTO Information (Id, name, age, arg_mark, classfication) VALUES (:value1, :value2)")
		            .beanMapped()
		            .build();
		}

	// reader json
 	@Bean
	public JsonItemReader<Trade> itemReaderJson(){
		return new JsonItemReaderBuilder<Trade>()
				.jsonObjectReader(new JacksonJsonObjectReader<Trade>(Trade.class))
				.resource(new FileSystemResource("G:\\Job\\SpringBatch\\src\\main\\resources\\read.json"))
				.name("readerJson")
				.build();
				
	}
	@Autowired
	private DataSource dataSource;
	// reader Database
	@Bean
	public JdbcCursorItemReader<Information> itemReaderJDBC(){
		return new JdbcCursorItemReaderBuilder<Information>()
				.dataSource(dataSource)
				.name("InformationReader")
				.saveState(false)
				.sql("Select ID, NAME,AGE, AVG_MARK ,CLASSFICATION from Information where ID = 6")
				.rowMapper(new InformationRowMapper())
				.build();
	}
	
	// read multi file
	private Resource[] inputResources = {new FileSystemResource("G:\\Job\\SpringBatch\\src\\main\\resources\\Read.json"),
			new FileSystemResource("G:\\Job\\SpringBatch\\src\\main\\resources\\Read2.json")};
	@Bean
	public MultiResourceItemReader<Trade> multiResourceItemReader(){
		return new MultiResourceItemReaderBuilder<Trade>()
				.name("multiFile")
				.resources(inputResources)
				.delegate(itemReaderJson())
				.build();
	}
	
	
	// read hibernate
	@Autowired
	private SessionFactory sessionFactory;
	
	@Bean
	public HibernateCursorItemReader<Information> hibernateCursorItemReader(SessionFactory sessionFactory){
		return new HibernateCursorItemReaderBuilder<Information>()
				.name("hibernateReader")
				.sessionFactory(sessionFactory)
				.queryString("from Information")
				.build();
	}

	
	@Bean
	public Job job(JobRepository jobRepository ,PlatformTransactionManager transactionManager ) throws ClassNotFoundException {
		return new JobBuilder("data1" , jobRepository)
				.start(step3(jobRepository,transactionManager))
				.next(step2(jobRepository,transactionManager))
				.next(step1(jobRepository,transactionManager))
				.build();
	}

	
	@Bean
	public Step step1(JobRepository jobRepository , PlatformTransactionManager transactionManager) {
		return new StepBuilder("step1" , jobRepository)
				.<Information,Information> chunk(3,transactionManager)
				.reader(itemReaderJDBC())
				.processor(new InforItemProcessor())
				.writer(itemWriter2())
				.listener(JobComponentNotificationListener.class)
				.build();
	}
	
	
	@Bean
	public Step step2(JobRepository jobRepository , PlatformTransactionManager transactionManager) throws ClassNotFoundException  {
		return new StepBuilder("step2" , jobRepository)
				.<Trade, Trade> chunk(3,transactionManager)
				.reader(multiResourceItemReader())
				.processor(new TradeProcessor())
				.writer(itemWriterJson())
				.build();
	}
	
	@Bean 
	public Step step3(JobRepository jobRepository , PlatformTransactionManager transactionManager) { 
		return new StepBuilder("step3" , jobRepository)
				.<Information, Information>chunk(3,transactionManager)
				.reader(new InformationReader())
				.processor(new InforItemProcessor())
				.writer(new InformationWriter(informationRepository))
				.build();
	}
}
