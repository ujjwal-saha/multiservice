package com.ezyreach.multiservice.dto.aeps;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginStatusRequest implements Serializable {

	private static final long serialVersionUID = -8967829485860603364L;
	private String type;
}
