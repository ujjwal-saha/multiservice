package com.ezyreach.multiservice.persistence.entity.stakeholder;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="t_stakeholders")
public class Stakeholder implements Serializable{

	private static final long serialVersionUID = 5615474870407369320L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @Column(name="name")
	private String name;
	
	@Column(name="mobile")
	private String mobile;	
	
	@Column(name="role")
	private String role;
	
	@Column(name="status")
	private String status;
	
	@Column(name="longitute")
	private String longitute;
	
	@Column(name="latitute")
	private String latitute;
	
	@ManyToOne
    @JoinColumn(name = "parent_id")
	private Stakeholder parentId;	

	@ManyToOne
    @JoinColumn(name = "tanent_id")
	private Stakeholder tanentId;

	@ManyToOne
    @JoinColumn(name = "created_by")
	private Stakeholder createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@ManyToOne
    @JoinColumn(name = "modified_by")
	private Stakeholder modifiedBy;
	
	@Column(name="modified_date")
	private Date modifiedDate;
	
	
}
