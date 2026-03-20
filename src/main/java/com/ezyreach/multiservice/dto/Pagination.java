package com.ezyreach.multiservice.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class Pagination implements Serializable{
	private static final long serialVersionUID = -9125929170007631808L;
	
	private Integer pageSize;
	private Integer pageNumber;
	private Integer totalPages;
	private String sortedBy;
	private String sortOrder;
	private String searchText;

}
