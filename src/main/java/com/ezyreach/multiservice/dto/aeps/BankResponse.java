package com.ezyreach.multiservice.dto.aeps;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BankResponse implements Serializable{

    private static final long serialVersionUID = 142984801864727954L;
	private String statuscode;
    private String actcode;
    private String status;
    private List<BankDTO> data;
    private String timestamp;
    private String ipay_uuid;
    private String orderid;
    private String environment;
    private String internalCode;
}
