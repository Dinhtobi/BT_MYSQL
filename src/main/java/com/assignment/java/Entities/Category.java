package com.assignment.java.Entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name ="Category")
public class Category {

	@Id
	private int id;
	
	@Column
	private String name ;
	
	@Column
	private Date createdAt;
	
	@Column
	private Date updatedAt;
}
