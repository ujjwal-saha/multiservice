package com.ezyreach.multiservice.dto.finpay;

import java.io.Serializable;

import lombok.Data;

@Data
public class SendOtp implements Serializable {

    private int superMerchantId;
    private String merchantLoginId;
    private String transactionType;
    private String mobileNumber;
    private String aadharNumber;
    private String panNumber;
    private String matmSerialNumber;
    private double latitude;
    private double longitude;

}
