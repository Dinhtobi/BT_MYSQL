package com.assignment.java.Mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.assignment.java.DTO.Payload.Request.CsvRequest;

public class CsvProductMapper implements FieldSetMapper<CsvRequest> {
	
	private static final int ID_COLUMN = 0;
	
	private static final int NAME_COLUMN = 1;
	
	private static final int PRICE_COLUMN = 2;

	private static final int NUMBER_COLUMN = 3;
	
	private static final int CATEGORY_COLUMN = 4;



	@Override
	public CsvRequest mapFieldSet(FieldSet fieldSet) throws BindException {
		CsvRequest csvRequest= new CsvRequest();
		csvRequest.setId(fieldSet.readInt(ID_COLUMN));
		csvRequest.setName(fieldSet.readString(NAME_COLUMN));
		csvRequest.setPrice(fieldSet.readInt(PRICE_COLUMN));
		csvRequest.setNumber(fieldSet.readInt(NUMBER_COLUMN));
		csvRequest.setCategory(fieldSet.readString(CATEGORY_COLUMN));
		return csvRequest;
	}
	

}
