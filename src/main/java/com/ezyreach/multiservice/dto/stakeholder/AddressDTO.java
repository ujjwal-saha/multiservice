package com.ezyreach.multiservice.dto.stakeholder;

import java.io.Serializable;

import lombok.Data;

@Data
public class AddressDTO implements Serializable{

	private static final long serialVersionUID = 2716196582033244971L;
	private Integer id;
	private String city;
	private String district;
	private String state;
	private String pin;
	private String address;
	private String landmark;
	private Integer stkId;

}
