package com.ezyreach.multiservice.dto.aeps;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class AEPSResponse implements Serializable {

	private static final long serialVersionUID = -6986891573734821145L;
	private String statuscode;
    private String actcode;
    private String status;
    private BalanceDetails data;
    private String timestamp;
    private String ipay_uuid;
    private String orderid;
    private String environment;
    private String internalCode;
}
