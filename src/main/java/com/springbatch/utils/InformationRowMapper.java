package com.springbatch.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springbatch.model.Information;

public class InformationRowMapper implements RowMapper<Information>{

	public static final String ID_COLUMN = "id";
	public static final String NAME_COLUMN = "name";
	public static final String AGE_COLUMN = "age";
	public static final String AVGMARK_COLUMN = "avg_mark";
	public static final String CLASSFICATION_COLUMN = "classfication";
	@Override
	public Information mapRow(ResultSet rs, int rowNum) throws SQLException {
		Information information =new Information();
		information.setId(rs.getInt(ID_COLUMN));
		information.setName(rs.getString(NAME_COLUMN));
		information.setAge(rs.getInt(AGE_COLUMN));
		information.setAvgMark(rs.getDouble(AVGMARK_COLUMN));
		information.setClassfication(rs.getString(CLASSFICATION_COLUMN));
			
		return information;
	}
	
}
