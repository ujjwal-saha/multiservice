package com.ezyreach.multiservice.dto;
import java.io.Serializable;

import lombok.Data;

@Data
public class LoginDTO implements Serializable{ 

	private static final long serialVersionUID = -2835744320876390175L;
	private String email;   
    private String password;
    private String newEmail;
}

