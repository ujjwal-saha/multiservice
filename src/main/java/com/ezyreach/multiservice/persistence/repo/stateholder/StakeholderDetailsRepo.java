package com.ezyreach.multiservice.persistence.repo.stateholder;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ezyreach.multiservice.persistence.entity.stakeholder.StakeholderDetails;

public interface StakeholderDetailsRepo extends JpaRepository<StakeholderDetails, Integer>,  JpaSpecificationExecutor<StakeholderDetails>{
    Optional<StakeholderDetails> findByUserName(String email);
    Optional<StakeholderDetails> findByUserNameAndPassword(String email,String password);
    Optional<StakeholderDetails> findByUserNameAndLoginPassword(String email,String loginPassword);
    StakeholderDetails findByStakeholderId(Integer stkId);

}
