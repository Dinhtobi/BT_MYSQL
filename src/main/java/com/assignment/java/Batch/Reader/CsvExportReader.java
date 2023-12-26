package com.assignment.java.Batch.Reader;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;

import com.assignment.java.Entities.Product;
import com.assignment.java.Mapper.ProductMapper;

public class CsvExportReader implements ItemReader<Product> {
	private final DataSource dataSource;
	
	public CsvExportReader(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private  int index = 0;
	
	private  List<Product> products = new ArrayList<Product>();

	@Override
	public Product read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		JdbcCursorItemReader<Product> itemReader = new JdbcCursorItemReader<Product>();
		if(index == 0) {
			
			itemReader.setDataSource(dataSource);
			itemReader.setSql("SELECT p.ID, p.NAME, p.PRICE, p.NUMBER, c.ID as category_id, c.NAME as category_name FROM finaljava.product as p\r\n"
					+ "inner join finaljava.category  as c on p.category_id = c.ID");
			itemReader.setRowMapper(new ProductMapper());
			ExecutionContext executionContext = new ExecutionContext();
			itemReader.open(executionContext);
			Product product ;
			while((product = itemReader.read()) !=null) {
				products.add(product);
			}
		}
		
		if(index >= products.size()) {
			return null;
		}
		index++;
		return products.get(index-1);
	}

	
}
