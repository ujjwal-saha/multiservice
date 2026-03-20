package com.ezyreach.multiservice.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ezyreach.multiservice.dto.Pagination;

@Service
public class PaginationService {

    public Pageable getPagination(Pagination pagination, String defaultSortOrder,String defaultSortedBy) {
		if(pagination!=null && pagination.getSortedBy()==null) {
			pagination.setSortedBy(defaultSortedBy);
		}
		if(pagination!=null && pagination.getSortOrder()==null) {
			pagination.setSortOrder(defaultSortOrder);
		}
		Sort sort=Sort.by(Direction.fromString(pagination.getSortOrder()), pagination.getSortedBy());
		Pageable pageble = PageRequest.of(pagination.getPageNumber(), pagination.getPageSize(),sort);
		
		return pageble;
	}

}
