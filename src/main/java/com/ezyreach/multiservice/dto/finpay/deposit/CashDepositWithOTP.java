package com.ezyreach.multiservice.dto.finpay.deposit;

import java.io.Serializable;

import lombok.Data;

@Data
public class CashDepositWithOTP implements Serializable{
     
    private int superMerchantId ;//M 
    private String merchantUserName; //M 
    private String merchantPin; //M 
    private String subMerchantId; //O 
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
    private String fingpayTransactionId;// value “” in the 1st leg 
    private String otp;// value “” in the 1st leg 
    private int cdPkId;  //value ‘0’ in the 1st leg 
    private String paymentType; //B-bank M
}
