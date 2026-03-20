package com.ezyreach.multiservice.persistence.repo.stateholder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ezyreach.multiservice.persistence.entity.stakeholder.Stakeholder;

public interface StakeholderRepo extends JpaRepository<Stakeholder, Integer>,  JpaSpecificationExecutor<Stakeholder>{

}
