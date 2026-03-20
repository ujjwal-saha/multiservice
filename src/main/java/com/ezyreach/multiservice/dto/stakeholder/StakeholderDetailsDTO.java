package com.ezyreach.multiservice.dto.stakeholder;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class StakeholderDetailsDTO implements Serializable {

	private static final long serialVersionUID = 3037714037663019235L;
	private Integer id;
	private String name;
	private String email;
	private String role;
	private String mobile;
	private AddressDTO address;
	private String status;
	private Integer parentId;
	private Integer tanentId;
	private Integer createdBy;
	private Date createdDate;
	private Integer modifiedBy;
	private Date modifiedDate;
	private String longitute;
	private String latitute;

}
