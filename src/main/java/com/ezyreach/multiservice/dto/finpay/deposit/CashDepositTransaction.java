package com.ezyreach.multiservice.dto.finpay.deposit;

import java.io.Serializable;

import lombok.Data;

@Data
public class CashDepositTransaction implements Serializable{
    private int superMerchantId ;//M 
    private String merchantUserName; //M 
    private String merchantPin; //M 
    private String secretKey; //M 
    private String mobileNumber; //M 
    private String iin; //M 
    
    private String transactionType;// cash deposit with otp - CDO//M 
    private double latitude; //M 
    private double longitude; //M 
    private String requestRemarks; //O 
    private String merchantTranId; //M 
    private String accountNumber; //M 
    private double amount; //M 
    private String fingpayTransactionId;// M 
    private String otp;// M 
    private int cdPkId; //M 
    private String paymentType;//B-bank M 
}
