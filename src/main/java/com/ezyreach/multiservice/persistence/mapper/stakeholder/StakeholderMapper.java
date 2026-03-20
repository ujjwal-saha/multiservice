package com.ezyreach.multiservice.persistence.mapper.stakeholder;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.ezyreach.multiservice.dto.stakeholder.AddressDTO;
import com.ezyreach.multiservice.dto.stakeholder.StakeholderDTO;
import com.ezyreach.multiservice.dto.stakeholder.StakeholderDetailsDTO;
import com.ezyreach.multiservice.persistence.entity.stakeholder.Address;
import com.ezyreach.multiservice.persistence.entity.stakeholder.Stakeholder;

@Component
public class StakeholderMapper {

    private final ModelMapper stakeholderMapper;
    private final ModelMapper stakeholderDetailsMapper;
    private final ModelMapper addressMapper;

	public StakeholderMapper() {
		stakeholderMapper = this.stakeholderConfigMapper();
		stakeholderDetailsMapper = this.stakeholderDetailsConfigMapper();
		addressMapper = this.addressConfigMapper();
	}
	
	private ModelMapper stakeholderConfigMapper() {
		ModelMapper mapper=new ModelMapper();
		PropertyMap<Stakeholder, StakeholderDTO> mapToDto = new PropertyMap<Stakeholder, StakeholderDTO>() {

			@Override
			protected void configure() {
				// TODO Auto-generated method stub

			}
		};

		PropertyMap<StakeholderDTO, Stakeholder> mapToEntity = new PropertyMap<StakeholderDTO, Stakeholder>() {

			@Override
			protected void configure() {
				// TODO Auto-generated method stub

			}
		};

		mapper.addMappings(mapToDto);
		mapper.addMappings(mapToEntity);
		return mapper;
	}
	
	private ModelMapper stakeholderDetailsConfigMapper() {
		ModelMapper mapper=new ModelMapper();
		PropertyMap<Stakeholder, StakeholderDetailsDTO> mapToDto = new PropertyMap<Stakeholder, StakeholderDetailsDTO>() {

			@Override
			protected void configure() {
				// TODO Auto-generated method stub
				this.map().setParentId(this.source.getParentId().getId());
				this.map().setCreatedBy(this.source.getCreatedBy().getId());
				this.map().setTanentId(this.source.getTanentId().getId());
				this.when(Conditions.isNotNull()).map().setModifiedBy(this.source.getModifiedBy().getId());

			}
		};

		PropertyMap<StakeholderDetailsDTO, Stakeholder> mapToEntity = new PropertyMap<StakeholderDetailsDTO, Stakeholder>() {

			@Override
			protected void configure() {
				// TODO Auto-generated method stub

			}
		};

		mapper.addMappings(mapToDto);
		mapper.addMappings(mapToEntity);
		return mapper;
	}
	
	private ModelMapper addressConfigMapper() {
		ModelMapper mapper=new ModelMapper();
		PropertyMap<Address, AddressDTO> mapToDto = new PropertyMap<Address, AddressDTO>() {

			@Override
			protected void configure() {
				// TODO Auto-generated method stub

			}
		};

		PropertyMap<AddressDTO, Address> mapToEntity = new PropertyMap<AddressDTO, Address>() {

			@Override
			protected void configure() {
				// TODO Auto-generated method stub

			}
		};

		mapper.addMappings(mapToDto);
		mapper.addMappings(mapToEntity);
		return mapper;
	}

	public StakeholderDTO toStakeholderDTO(Stakeholder entity) {
		return this.stakeholderMapper.map(entity, StakeholderDTO.class);
	}

	public Stakeholder toStakeholderEntity(StakeholderDTO dto) {
		return this.stakeholderMapper.map(dto,Stakeholder.class);
	}
	
	public StakeholderDetailsDTO toStakeholderDetailsDTO(Stakeholder entity) {
		return this.stakeholderDetailsMapper.map(entity, StakeholderDetailsDTO.class);
	}

	public Stakeholder toStakeholderDetailsEntity(StakeholderDetailsDTO dto) {
		return this.stakeholderDetailsMapper.map(dto,Stakeholder.class);
	}
	
	public AddressDTO toAddressDTO(Address entity) {
		return this.addressMapper.map(entity, AddressDTO.class);
	}

	public Address toAddressEntity(AddressDTO dto) {
		return this.addressMapper.map(dto,Address.class);
	}

}
