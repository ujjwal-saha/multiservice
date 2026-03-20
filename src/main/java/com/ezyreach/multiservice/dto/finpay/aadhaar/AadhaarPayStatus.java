package com.ezyreach.multiservice.dto.finpay.aadhaar;

import java.io.Serializable;

import lombok.Data;

@Data
public class AadhaarPayStatus implements Serializable{

    private String merchantTranId ; 
    private String hash; 
    private String merchantLoginId; 
    private String merchantPassword; 
    private int superMerchantId; 
    private String superMerchantPassword; 

}
