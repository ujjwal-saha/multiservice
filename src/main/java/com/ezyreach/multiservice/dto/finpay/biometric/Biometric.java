package com.ezyreach.multiservice.dto.finpay.biometric;

import java.io.Serializable;

import lombok.Data;

@Data
public class Biometric implements Serializable{
    private P2CardnumberORUID cardnumberORUID; 
    private String requestRemarks; 
    private CaptureResponse captureResponse; 
    private String merchantLoginId; 
    private int superMerchantId; 
    private int primaryKeyId; 
    private String encodeFPTxnId; 
}
