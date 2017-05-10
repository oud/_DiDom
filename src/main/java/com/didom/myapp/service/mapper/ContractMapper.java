package com.didom.myapp.service.mapper;

import com.didom.myapp.domain.*;
import com.didom.myapp.service.dto.ContractDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Contract and its DTO ContractDTO.
 */
@Mapper(componentModel = "spring", uses = {ClientMapper.class, FreelancerMapper.class, ProposalMapper.class, PaymentTypeMapper.class, })
public interface ContractMapper {

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "freelancer.id", target = "freelancerId")
    @Mapping(source = "proposal.id", target = "proposalId")
    @Mapping(source = "paymentType.id", target = "paymentTypeId")
    ContractDTO contractToContractDTO(Contract contract);

    List<ContractDTO> contractsToContractDTOs(List<Contract> contracts);

    @Mapping(source = "clientId", target = "client")
    @Mapping(source = "freelancerId", target = "freelancer")
    @Mapping(source = "proposalId", target = "proposal")
    @Mapping(source = "paymentTypeId", target = "paymentType")
    Contract contractDTOToContract(ContractDTO contractDTO);

    List<Contract> contractDTOsToContracts(List<ContractDTO> contractDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Contract contractFromId(Long id) {
        if (id == null) {
            return null;
        }
        Contract contract = new Contract();
        contract.setId(id);
        return contract;
    }
    

}
