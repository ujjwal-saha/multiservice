package com.ezyreach.multiservice.dto.finpay;

import java.io.Serializable;
import lombok.Data;

@Data
public class MerchantKycAddressDataModel implements Serializable{

    private String shopAddress; 
    private String shopCity; 
    private String shopDistrict; 
    private Integer shopState; 
    private String shopPincode; 
    private Double shopLatitude; 
    private Double shopLongitude; 
    private String backgroundImageOfShop; 
    
}
