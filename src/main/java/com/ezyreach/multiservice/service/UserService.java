package com.ezyreach.multiservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.ezyreach.multiservice.dto.LoginDTO;
import com.ezyreach.multiservice.dto.Pagination;
import com.ezyreach.multiservice.dto.ResponseDTO;
import com.ezyreach.multiservice.dto.stakeholder.AddressDTO;
import com.ezyreach.multiservice.dto.stakeholder.StakeholderDTO;
import com.ezyreach.multiservice.dto.stakeholder.StakeholderDetailsDTO;
import com.ezyreach.multiservice.exception.MultiserviceException;
import com.ezyreach.multiservice.persistence.entity.stakeholder.Address;
import com.ezyreach.multiservice.persistence.entity.stakeholder.Stakeholder;
import com.ezyreach.multiservice.persistence.entity.stakeholder.StakeholderDetails;
import com.ezyreach.multiservice.persistence.mapper.stakeholder.StakeholderMapper;
import com.ezyreach.multiservice.persistence.repo.stateholder.AddressRepo;
import com.ezyreach.multiservice.persistence.repo.stateholder.StakeholderDetailsRepo;
import com.ezyreach.multiservice.persistence.repo.stateholder.StakeholderRepo;
import com.ezyreach.multiservice.persistence.specification.UserSpec;
import com.ezyreach.multiservice.util.EncryptorDecryptor;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	// creating a logger
	Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private StakeholderDetailsRepo stakeholderDetailsRepo;
	@Autowired
	private StakeholderRepo stakeholderRepo;
	@Autowired
	private AddressRepo addressRepo;
	@Autowired
	private StakeholderMapper stakeholderMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PaginationService paginationService;

	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDTO<String> saveStakeholder(StakeholderDTO stakeholderDTO) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			StakeholderDetails existingUser = stakeholderDetailsRepo.findByUserName(stakeholderDTO.getEmail())
					.orElse(new StakeholderDetails());
			if (existingUser.getUsername() != null && !existingUser.getUsername().trim().isEmpty()
					&& stakeholderDTO.getId() == null) {
				throw new MultiserviceException(
						stakeholderDTO.getEmail() + " is available for other user, please put different email id");

			} else {
				Stakeholder stakeholder = stakeholderMapper.toStakeholderEntity(stakeholderDTO);
				Stakeholder createdBy = new Stakeholder();
				createdBy.setId(stakeholderDTO.getCreatedBy());
				stakeholder.setCreatedBy(createdBy);

				Stakeholder parent = new Stakeholder();
				parent.setId(stakeholderDTO.getParentId());
				stakeholder.setParentId(parent);

				Stakeholder tanent = new Stakeholder();
				tanent.setId(stakeholderDTO.getTanentId());
				stakeholder.setTanentId(tanent);

				stakeholder.setStatus("Active");
				if (stakeholderDTO.getCreatedBy() != null && stakeholder.getId() == null) {
					stakeholder.setCreatedDate(new Date());
				}
				if (stakeholderDTO.getModifiedBy() != null) {
					stakeholder.setModifiedDate(new Date());
				}
				StakeholderDetails stakeholderDetails = new StakeholderDetails();
				stakeholderDetails.setPassword(passwordEncoder.encode(stakeholderDTO.getPassword().trim()));
				stakeholderDetails.setUserName(stakeholderDTO.getEmail());
				stakeholderDetails.setLoginPassword(EncryptorDecryptor.encrypt(stakeholderDTO.getPassword().trim()));
				stakeholderDetails.setStakeholder(stakeholder);
				StakeholderDetails latestStakeholder = stakeholderDetailsRepo.save(stakeholderDetails);
				if (latestStakeholder != null && latestStakeholder.getId() > 0) {
					AddressDTO address = stakeholderDTO.getAddress();
					address.setStkId(latestStakeholder.getStakeholder().getId());
					this.updateAddress(address);
					response.setCode(200);
					response.setMessage("Success");
					logger.info("Updated User Details - " + response);
				}
			}

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDTO<String> updateStakeholder(StakeholderDetailsDTO stakeholderDetailsDTO) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			Optional<Stakeholder> stakeholderOptional = stakeholderRepo.findById(stakeholderDetailsDTO.getId());
			if (stakeholderOptional.isPresent()) {
				Stakeholder stakeholder = stakeholderOptional.get();
				if (stakeholderDetailsDTO.getName() != null) {
					stakeholder.setName(stakeholderDetailsDTO.getName());
				}
				if (stakeholderDetailsDTO.getMobile() != null) {
					stakeholder.setMobile(stakeholderDetailsDTO.getMobile());
				}
				if (stakeholderDetailsDTO.getStatus() != null) {
					stakeholder.setStatus(stakeholderDetailsDTO.getStatus());
				}
				if (stakeholderDetailsDTO.getRole() != null) {
					stakeholder.setRole(stakeholderDetailsDTO.getRole());
				}
				if (stakeholderDetailsDTO.getModifiedBy() != null) {
					Stakeholder modifiedBy = new Stakeholder();
					modifiedBy.setId(stakeholderDetailsDTO.getModifiedBy());
					stakeholder.setModifiedBy(modifiedBy);
					stakeholder.setModifiedDate(new Date());
				}
				stakeholderRepo.save(stakeholder);
				response.setCode(200);
				response.setMessage("Success");
				logger.info("Updated User Details - " + response);
			}

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDTO<String> updateUserName(LoginDTO loginDTO) {
		ResponseDTO<String> response = new ResponseDTO<>();
		String message = null;
		try {

			StakeholderDetails stakeholderDetails = stakeholderDetailsRepo.findByUserName(loginDTO.getEmail())
					.orElse(new StakeholderDetails());
			if (stakeholderDetails != null && stakeholderDetails.getId() != null) {

				if (!stakeholderDetails.getUsername().equalsIgnoreCase(loginDTO.getNewEmail())) {
					stakeholderDetails.setUserName(loginDTO.getNewEmail());
					message = "User Name updated";
					stakeholderDetailsRepo.save(stakeholderDetails);
					response.setCode(200);
					response.setMessage("Success");
					logger.info(message + " - " + response);
				} else {
					throw new MultiserviceException(
							loginDTO.getNewEmail() + " is available, please try different one.");
				}
			} else {
				throw new MultiserviceException(loginDTO.getEmail()+" is not correct, please try connect Email ID");
			}			

		} catch (

		Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDTO<String> updateAddress(AddressDTO addressDTO) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {

			Address address = stakeholderMapper.toAddressEntity(addressDTO);
			Stakeholder stakeholder = new Stakeholder();
			stakeholder.setId(addressDTO.getStkId());
			address.setStateholder(stakeholder);
			addressRepo.save(address);
			response.setCode(200);
			response.setMessage("Success");
			logger.info("Updated Address Details - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}

	public StakeholderDetails authenticate(LoginDTO loginDTO) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

		return stakeholderDetailsRepo.findByUserName(loginDTO.getEmail()).orElseThrow();
	}

	public ResponseDTO<StakeholderDetailsDTO> login(LoginDTO loginDTO) {
		ResponseDTO<StakeholderDetailsDTO> response = new ResponseDTO<>();
		try {

			StakeholderDetails stakeholderDetails = stakeholderDetailsRepo.findByUserName(loginDTO.getEmail())
					.orElse(new StakeholderDetails());

			if (stakeholderDetails != null && stakeholderDetails.getId() > 0) {
				String rawPassword = EncryptorDecryptor.decrypt(stakeholderDetails.getLoginPassword());
				if (rawPassword.equals(loginDTO.getPassword())) {
					StakeholderDetailsDTO stakeholderDTO = stakeholderMapper
							.toStakeholderDetailsDTO(stakeholderDetails.getStakeholder());
					response.setCode(200);
					response.setMessage("Success");
					response.setData(stakeholderDTO);
				} else {
					throw new MultiserviceException("Wrong email id or password");
				}
			} else {
				throw new MultiserviceException("Wrong email id or password");
			}
		} catch (Exception ex) {
			log.error("Login failed === " + ex.getMessage());
			throw new MultiserviceException("Wrong email id or password");
		}

		return response;
	}

	public ResponseDTO<StakeholderDetailsDTO> fetchUserById(Integer id) {
		ResponseDTO<StakeholderDetailsDTO> response = new ResponseDTO<>();
		try {
			Stakeholder stakeholder = stakeholderRepo.findById(id).orElse(new Stakeholder());
			StakeholderDetailsDTO stakeholderDetailsDTO = stakeholderMapper.toStakeholderDetailsDTO(stakeholder);

			response.setCode(200);
			response.setMessage("Success");
			response.setData(stakeholderDetailsDTO);
		} catch (Exception ex) {
			throw new MultiserviceException("Bad User ID " + id);
		}

		return response;
	}

	public ResponseDTO<List<StakeholderDetailsDTO>> fetchAllUsers() {
		ResponseDTO<List<StakeholderDetailsDTO>> response = new ResponseDTO<>();
		try {
			List<Stakeholder> userPrifiles = stakeholderRepo.findAll();
			List<StakeholderDetailsDTO> StakeholderDetails = userPrifiles.stream()
					.map(stakeholderMapper::toStakeholderDetailsDTO).collect(Collectors.toList());
			response.setData(StakeholderDetails);
		} catch (Exception ex) {
			throw new MultiserviceException("No User Available");
		}
		return response;
	}

	public ResponseDTO<String> updatePassword(LoginDTO loginDTO) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {

			StakeholderDetails stakeholderDetails = stakeholderDetailsRepo.findByUserName(loginDTO.getEmail())
					.orElse(new StakeholderDetails());
			if (stakeholderDetails != null && stakeholderDetails.getId() > 0) {
				stakeholderDetails.setPassword(loginDTO.getPassword());
				stakeholderDetailsRepo.save(stakeholderDetails);
				response.setCode(200);
				response.setMessage("Success");
			}

		} catch (Exception ex) {
			throw new MultiserviceException("Wrong email id put in");
		}

		return response;
	}

	public ResponseDTO<List<StakeholderDetailsDTO>> fetchAllUsers(Pagination pagination) {
		ResponseDTO<List<StakeholderDetailsDTO>> response = new ResponseDTO<>();
		String message = "User doesn't found";
		Page<Stakeholder> users = null;
		Pageable pageable = paginationService.getPagination(pagination, "ASC", "name");
		try {
			if (pagination != null && pagination.getSearchText() != null && !pagination.getSearchText().isEmpty()) {
				Specification<Stakeholder> spec = UserSpec.likeField("name", pagination.getSearchText())
						.or(UserSpec.likeField("mobile", pagination.getSearchText()))
						.or(UserSpec.likeField("userType", pagination.getSearchText()))
						.or(UserSpec.likeField("email", pagination.getSearchText()));
				users = stakeholderRepo.findAll(spec, pageable);
			} else {
				users = stakeholderRepo.findAll(pageable);
			}

			if (!ObjectUtils.isEmpty(users)) {
				pagination.setTotalPages(users.getTotalPages());
				List<StakeholderDetailsDTO> stakeholderDetailsDTODTOs = users.getContent().stream()
						.map(stakeholderMapper::toStakeholderDetailsDTO).collect(Collectors.toList());
				response.setData(stakeholderDetailsDTODTOs);
				response.setMessage("Success");
				response.setCode(HttpStatus.OK.value());
				response.setPagination(pagination);
			} else {
				throw new MultiserviceException(message);
			}
		} catch (Exception e) {
			log.error(message, e);
			throw new MultiserviceException(message, e);
		}
		return response;
	}

}
