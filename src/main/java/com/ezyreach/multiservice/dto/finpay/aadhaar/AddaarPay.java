package com.ezyreach.multiservice.dto.finpay.aadhaar;

import java.io.Serializable;

import lombok.Data;

@Data
public class AddaarPay implements Serializable{
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
    private String merchantTransactionId;
    private String merchantUserName; 
    private String merchantPin; 
    private String subMerchantId; 
    private String superMerchantId; 



    @Data
    public static class P2CardnumberORUID{
        private String adhaarNumber; 
        private int  indicatorforUID; 
        private String nationalBankIdentificationNumber; 
        private String virtualId; //(only if you are sending virtual id) 
    }

    @Data
    public static class CaptureResponse{
        private String errCode; 
        private String errInfo; 
        private String fCount; 
        private String fType; 
        private String iCount; 
        private String iType; 
        private String pCount; 
        private String pType; 
        private String nmPoints; 
        private String qScore; 
        private String dpID; 
        private String rdsID; 
        private String rdsVer; 
        private String dc; 
        private String mi; 
        private String mc; 
        private String ci; 
        private String sessionKey; 
        private String hmac; 
        private String PidDatatype; 
        private String Piddata; 

    }

}
