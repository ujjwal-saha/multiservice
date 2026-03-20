package com.ezyreach.multiservice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginResponseDTO implements Serializable {

	private static final long serialVersionUID = 2708476329824073156L;
	private String token;
    private long expiresIn;
}
