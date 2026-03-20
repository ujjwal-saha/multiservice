package com.ezyreach.multiservice.dto.aeps;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BankDTO implements Serializable{

    private static final long serialVersionUID = -3657298592056296957L;
	private Long bankId;
    private String name;
    private String iin;
    private Boolean aepsEnabled;
    private Boolean aadhaarpayEnabled;
    private String aepsFailureRate;
    private String aadhaarpayFailureRate;
}
