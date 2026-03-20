package com.ezyreach.multiservice.dto.aeps;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class AEPSRequest implements Serializable{
    private static final long serialVersionUID = 6707283792024458508L;
	private String type;
    private String serviceType;
    private String bankiin;
    private String latitude;
    private String longitude;
    private String mobile;
    private String amount;
    private String externalRef;
    private String captureType;
    private BiometricDetails biometricData;
}
