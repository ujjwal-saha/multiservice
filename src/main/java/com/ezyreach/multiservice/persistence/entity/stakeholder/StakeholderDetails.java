package com.ezyreach.multiservice.persistence.entity.stakeholder;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="t_stateholder_details")
public class StakeholderDetails implements UserDetails{

	private static final long serialVersionUID = 6443623898927598452L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="password")
	private String password;
	
	@Column(name="login_pass")
	private String loginPassword;

	@Column(name="user_name")
	private String userName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="stk_id")
	private Stakeholder stakeholder; 	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getPassword() {
		return password.trim();
	}

	@Override
	public String getUsername() {	
		if(userName!=null )
		  return userName.trim();
		return null;
	}

	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
