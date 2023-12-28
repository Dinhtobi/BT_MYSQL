package com.assignment.java.DTO.Payload.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PageResponse<T> {
	private List<T> content;
	
	private int page;
	
	private int size;
	
	private long totalElement;
	
	private int totalPages;
	
	private boolean last;

	public PageResponse(List<T> list, int page, int size, long totalElement, int totalPages, boolean last) {
		this.content = list;
		this.page = page;
		this.size = size;
		this.totalElement = totalElement;
		this.totalPages = totalPages;
		this.last = last;
	}


	public List<T> getContent() {
		return content == null ? null : new ArrayList<>(content);
	}

	public void setContent(List<T> content) {
		if(content == null) {
			this.content = null;
		}else {
			this.content = Collections.unmodifiableList(content);
		}
	}

	public boolean isLast() {
		return last;
	}

	
	
	
}
