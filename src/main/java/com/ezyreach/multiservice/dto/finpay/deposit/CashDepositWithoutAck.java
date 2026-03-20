package com.ezyreach.multiservice.dto.finpay.deposit;

import java.io.Serializable;
import lombok.Data;

@Data
public class CashDepositWithoutAck implements Serializable{
    private String rrn; 
    private String merchantTransactionId; 
    private Boolean acknowledgementStatus; 
    private String fingpayTransactionId; 
    private String responseCode; 
}
