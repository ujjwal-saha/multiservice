package com.ezyreach.multiservice.dto.finpay.transaction;

import java.io.Serializable;

import lombok.Data;

@Data
public class P2CardnumberORUID implements Serializable{
    private String adhaarNumber; 
    private int  indicatorforUID; 
    private String nationalBankIdentificationNumber; 
    private String virtualId; //(only if you are sending virtual id) 
}
