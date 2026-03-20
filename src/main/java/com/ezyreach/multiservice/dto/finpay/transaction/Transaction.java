package com.ezyreach.multiservice.dto.finpay.transaction;

import java.io.Serializable;

import lombok.Data;

@Data
public class Transaction implements Serializable{
   private P2CardnumberORUID cardnumberORUID; 
    private String mobileNumber; 
    private String paymentType; 
    private String timestamp; 
    private String transactionType; 
    private double latitude; 
    private double longitude; 
    private String requestRemarks; 
    private String deviceTransactionId; 
    private CaptureResponse captureResponse; 
    private String languageCode; 
    private double transactionAmount; 
    private String merchantTranId; //(For cash withdrawal) 
    private String merchantTransactionId; //(For balance enquiry) 
    private String merchantUserName; 
    private String merchantPin; 
    private String subMerchantId; 
    private String superMerchantId; 
}
