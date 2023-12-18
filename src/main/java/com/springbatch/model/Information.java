package com.springbatch.model;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="Information")
public class Information {
	
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private int age;
	
	private double avgMark;
	
	private String classfication;
	
	public String toString() {
		return "Name: "  + name  +", age:" + age + ", avgMark:" + avgMark +", classfiication" + classfication;
	}

	public Information(String name, int age, double avgMark, String classfication) {
		super();
		this.name = name;
		this.age = age;
		this.avgMark = avgMark;
		this.classfication = classfication;
	}
	
}
