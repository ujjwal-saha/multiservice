package com.ezyreach.multiservice.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO implements Serializable{

	private static final long serialVersionUID = -8993346294151368178L;
	private Integer id;
    @NotBlank(message="Name is mandatory")
    private String name;
    @NotBlank(message="Email is mandatory")
    private String email;
    @NotBlank(message="Password is mandatory")
    private String pass;
    @NotBlank(message="Mobile is mandatory")
    private String mobile;
    private String address;
    @NotBlank(message="Role is mandatory")
    private String role;
    private Integer parentId;
    private Integer tanentId;
    private Integer createdBy;
    private Integer modifiedBy;
}
