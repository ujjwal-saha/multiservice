package com.ezyreach.multiservice.persistence.specification;

import org.springframework.data.jpa.domain.Specification;

import com.ezyreach.multiservice.persistence.entity.stakeholder.Stakeholder;

public class UserSpec {

    	
	public static  Specification<Stakeholder> likeField(String fieldName, String value) {
		return (root, query, builder) -> {
			return builder.like(root.get(fieldName), "%" + value + "%");
		};
	}
	
/*	
	public static  Specification<UserProfile> likeItemCompany(String fieldName, String value) {
		return (root, query, builder) -> {
			Join<ItemCompany,Model> modelCompany = root.join("itemCompany");
			
			return builder.like(modelCompany.get(fieldName), "%" + value + "%");
		};
	}
	
	public static  Specification<UserProfile> likeItem(String fieldName, String value) {
		return (root, query, builder) -> {
			Join<ItemCompany,Model> modelCompany = root.join("itemCompany");
			Join<Item,ItemCompany> item = modelCompany.join("item");
			return builder.like(item.get(fieldName), "%" + value + "%");
		};
	}*/
}
