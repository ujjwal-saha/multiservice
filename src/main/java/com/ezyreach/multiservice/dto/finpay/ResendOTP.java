package com.ezyreach.multiservice.dto.finpay;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResendOTP implements Serializable{
    private int superMerchantId;
    private String merchantLoginId;
    private int primaryKeyId;
    private String encodeFPTxnId;
}
