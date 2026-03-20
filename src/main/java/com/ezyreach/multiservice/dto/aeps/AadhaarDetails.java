package com.ezyreach.multiservice.dto.aeps;

import lombok.Data;

import java.io.Serializable;

@Data
public class AadhaarDetails implements Serializable {

	private static final long serialVersionUID = 7150179389901437872L;
	private Boolean isFaceAuthAvailable;
    private String aadhaarLastFour;
    private Boolean isTxnBioLoginRequired;
}
