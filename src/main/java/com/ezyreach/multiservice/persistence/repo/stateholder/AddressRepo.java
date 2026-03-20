package com.ezyreach.multiservice.persistence.repo.stateholder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ezyreach.multiservice.persistence.entity.stakeholder.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer>,  JpaSpecificationExecutor<Address>{

}
