package com.ezyreach.multiservice.dto.stakeholder;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StakeholderDTO implements Serializable{

	private static final long serialVersionUID = 4402996513728864796L;
	private Integer id;
    @NotBlank(message="Name is mandatory")
    private String name;
    @NotBlank(message="Email is mandatory")
    private String email;
    @NotBlank(message="Password is mandatory")
    private String password;
    @NotBlank(message="Mobile is mandatory")
    private String mobile;
    private AddressDTO address;
    @NotBlank(message="Role is mandatory")
    private String role;
    private Integer parentId;
    private Integer tanentId;
    private Integer createdBy;
    private Integer modifiedBy;
    private String longitute;
    private String latitute;

}
