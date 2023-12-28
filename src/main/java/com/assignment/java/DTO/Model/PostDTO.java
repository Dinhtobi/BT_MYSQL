package com.assignment.java.DTO.Model;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDTO {
	private int id;
	private String title;
	private String description;
	private String typeEvent;
	private Date createdAt;
	private Date updatedAt;
}
