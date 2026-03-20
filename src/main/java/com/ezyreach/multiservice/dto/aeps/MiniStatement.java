package com.ezyreach.multiservice.dto.aeps;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class MiniStatement implements Serializable {

	private static final long serialVersionUID = -2579633677184136662L;
	private String date;
    private String txnType;
    private String amount;
    private String narration;
}
