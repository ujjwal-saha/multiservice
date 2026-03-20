package com.ezyreach.multiservice.dto.finpay;

import java.io.Serializable;

import lombok.Data;

@Data
public class Kyc implements Serializable{
	private static final long serialVersionUID = -6382163644756398222L;
	private String userPan; 
	private String aadhaarNumber; 
	private String gstinNumber; 
	private String companyOrShopPan; 
	private String merchantPanImage; 
	private String maskedAadharImage; 
	private String shopAndPanImage; 

}
