package com.ezyreach.multiservice.dto.finpay;

import java.io.Serializable;

import lombok.Data;

@Data
public class MerchantAddress implements Serializable{
	private static final long serialVersionUID = -4426041473368759677L;
	private String merchantAddress1; 
	private String merchantAddress2; 
	private Integer merchantState; 
	private String merchantCityName; 
	private String merchantDistrictName; 
	private String merchantPinCode; 
}
