package com.ezyreach.multiservice.dto.finpay;

import java.io.Serializable;

import lombok.Data;

@Data
public class Settlement implements Serializable{
	private static final long serialVersionUID = 6639328726021875097L;
	private String companyBankAccountNumber ; 
    private String bankIfscCode ; 
    private String companyBankName ; 
    private String bankAccountName ;   

}
