package com.ezyreach.multiservice.dto.finpay;

import java.io.Serializable;

import lombok.Data;

@Data
public class MerchantModel implements Serializable{

	private static final long serialVersionUID = -4841424781071799361L;
	private String     merchantLoginId ; 
	private String     merchantLoginPin ; 
	private String     firstName ; 
	private String     lastName ; 
	private String middleName; 
	private String     merchantPhoneNumber ; 
	private MerchantAddress  merchantAddress ; 
	private String     companyLegalName ; 
	private String dateOfIncorporation; 
	private String userType; 
	private Integer    companyType; 
	private String emailId; 
	private String certificateOfIncorporationImage; 
	private Kyc kyc; 
	private Settlement settlementV1; 
	private String tradeBusinessProof; 
	private String termsConditionCheck; 
	private String cancelledChequeImages; 
	private String physicalVerification; 
	private String videoKycWithLatLongData; 
	private MerchantKycAddressDataModel merchantKycAddressData; 

}
