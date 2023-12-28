package com.assignment.java.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.assignment.java.DTO.Payload.Request.CsvRequest;
import com.assignment.java.Entities.Category;
import com.assignment.java.Entities.Product;

public class ProductMapper implements RowMapper<Product> {

	private static final String ID_COLUMN = "id";

	private static final String NAME_COLUMN = "name";

	private static final String PRICE_COLUMN = "price";

	private static final String NUMBER_COLUMN = "number";

	private static final String CATEGORY_ID_COLUMN = "category_id";
	private static final String CATEGORY_NAME_COLUMN = "category_name";

	
	
	public static Product mapper(CsvRequest csvRequest) {
		Product product = new Product();
		product.setId(csvRequest.getId());
		product.setName(csvRequest.getName());
		product.setNumber(csvRequest.getNumber());
		product.setPrice(csvRequest.getPrice());
		return product;
	}

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product =new Product();
		product.setId(rs.getInt(ID_COLUMN));
		product.setName(rs.getString(NAME_COLUMN));
		product.setPrice(rs.getInt(PRICE_COLUMN));
		product.setNumber(rs.getInt(NUMBER_COLUMN));
		int category_id = rs.getInt(CATEGORY_ID_COLUMN);
		String category_name = rs.getString(CATEGORY_NAME_COLUMN);
		Category category = new Category();
		category.setId(category_id);
		category.setName(category_name);
		product.setCategory(category);
		return product;
	}

}
