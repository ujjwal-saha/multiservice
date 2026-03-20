package com.ezyreach.multiservice.dto.finpay.deposit;

import java.io.Serializable;

import lombok.Data;

@Data
public class CashDepositStatus implements Serializable{
    private String merchantTranId ; 
    private String hash; 
    private String merchantLoginId; 
    private String merchantPassword; 
    private int superMerchantId; 
    private String superMerchantPassword; 
}
