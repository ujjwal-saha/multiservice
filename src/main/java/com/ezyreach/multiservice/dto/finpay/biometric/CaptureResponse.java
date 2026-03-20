package com.ezyreach.multiservice.dto.finpay.biometric;

import java.io.Serializable;

import lombok.Data;

@Data
public class CaptureResponse implements Serializable{
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
