package com.ezyreach.multiservice.dto.finpay;

import java.io.Serializable;

import lombok.Data;

@Data
public class ValidateOTP implements Serializable{
    private int superMerchantId;
    private String merchantLoginId;
    private String otp;
    private int primaryKeyId;
    private String encodeFPTxnId;
}
