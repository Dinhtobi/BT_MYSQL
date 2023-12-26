package com.assignment.java.Entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Product")
public class Product {

	@jakarta.persistence.Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@Column
	private int price ;
	@Column
	private int number;
	@Column
	private Date createdAt;
	@Column
	private Date updatedAt;
	
	@OneToOne
	@JoinColumn(name = "category_id" , referencedColumnName = "id")
	private Category category;
}
