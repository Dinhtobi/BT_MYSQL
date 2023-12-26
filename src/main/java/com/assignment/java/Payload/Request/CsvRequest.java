package com.assignment.java.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsvRequest {
	private int id;
	private String name;
	private int price ;
	private int number;
	private String category;
}
