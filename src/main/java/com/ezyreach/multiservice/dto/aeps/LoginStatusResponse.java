package com.ezyreach.multiservice.dto.aeps;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginStatusResponse implements Serializable {

	private static final long serialVersionUID = 6131972126656482105L;
	private String statuscode;
    private String actcode;
    private String status;
    private AadhaarDetails data;
    private String timestamp;
    private String ipay_uuid;
    private String orderid;
    private String environment;
    private String internalCode;
}

