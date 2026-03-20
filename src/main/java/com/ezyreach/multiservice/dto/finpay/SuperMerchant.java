package com.ezyreach.multiservice.dto.finpay;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class SuperMerchant implements Serializable{

	private static final long serialVersionUID = -2205420038237128346L;
	private String username; 
	private String password; 
	private Date timestamp; 
	private double latitude; 
	private double longitude; 
	private String ipAddress; 
	private int supermerchantId; 
	private MerchantModel merchant; 

}
