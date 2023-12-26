package com.assignment.java.Repository.Projections;

import java.util.Date;

public interface DetailCategoryProjection {

	int getId();
	String getName();
	String getNameProduct();
	int getPriceProduct();
	int getNumberProduct();
	Date getCreateAt();
}
