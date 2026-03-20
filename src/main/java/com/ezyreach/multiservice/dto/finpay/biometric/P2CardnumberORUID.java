package com.ezyreach.multiservice.dto.finpay.biometric;

import java.io.Serializable;

import lombok.Data;

@Data
public class P2CardnumberORUID implements Serializable{
    private String adhaarNumber; 
    private int indicatorforUID; 
    private String nationalBankIdentificationNumber; 
}
