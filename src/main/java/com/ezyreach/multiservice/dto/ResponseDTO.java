package com.ezyreach.multiservice.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResponseDTO<T> implements Serializable{

	private static final long serialVersionUID = -6469906282338513744L;
	private Integer code;
    private String message;
    private T data;
    private Pagination pagination;
}
