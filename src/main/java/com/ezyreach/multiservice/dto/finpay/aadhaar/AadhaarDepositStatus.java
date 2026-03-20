package com.ezyreach.multiservice.dto.finpay.aadhaar;

import java.io.Serializable;

import lombok.Data;

@Data
public class AadhaarDepositStatus implements Serializable{

    private String merchantTranId ; 
    private String hash; 
    private String merchantLoginId; 

}
