package com.ezyreach.multiservice.persistence.entity.stakeholder;

import java.io.Serializable;

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
@Table(name = "t_address")
public class Address implements Serializable {

	private static final long serialVersionUID = 6223637115378164132L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "city")
	private String city;

	@Column(name = "district")
	private String district;

	@Column(name = "state")
	private String state;

	@Column(name = "address")
	private String address;

	@Column(name = "landmark")
	private String landmark;

	@Column(name = "pin")
	private String pin;
	
	@ManyToOne
	@JoinColumn(name="stk_id")
	private Stakeholder stateholder;
	
	
}
