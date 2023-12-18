package com.springbatch.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Trade {
	
	private String isin;
	
	private Long quantity;
	
	private BigDecimal price;
	
	private String customer;
	
}
