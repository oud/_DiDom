package com.didom.myapp.service.mapper;

import com.didom.myapp.domain.*;
import com.didom.myapp.service.dto.ProposalDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Proposal and its DTO ProposalDTO.
 */
@Mapper(componentModel = "spring", uses = {JobMapper.class, FreelancerMapper.class, PaymentTypeMapper.class, ProposalStatusCatalogMapper.class, })
public interface ProposalMapper {

    @Mapping(source = "job.id", target = "jobId")
    @Mapping(source = "freelancer.id", target = "freelancerId")
    @Mapping(source = "paymentType.id", target = "paymentTypeId")
    @Mapping(source = "currentProposalStatus.id", target = "currentProposalStatusId")
    @Mapping(source = "currentProposalStatus.statusName", target = "currentProposalStatusStatusName")
    ProposalDTO proposalToProposalDTO(Proposal proposal);

    List<ProposalDTO> proposalsToProposalDTOs(List<Proposal> proposals);

    @Mapping(source = "jobId", target = "job")
    @Mapping(source = "freelancerId", target = "freelancer")
    @Mapping(source = "paymentTypeId", target = "paymentType")
    @Mapping(source = "currentProposalStatusId", target = "currentProposalStatus")
    @Mapping(target = "contracts", ignore = true)
    @Mapping(target = "messages", ignore = true)
    Proposal proposalDTOToProposal(ProposalDTO proposalDTO);

    List<Proposal> proposalDTOsToProposals(List<ProposalDTO> proposalDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Proposal proposalFromId(Long id) {
        if (id == null) {
            return null;
        }
        Proposal proposal = new Proposal();
        proposal.setId(id);
        return proposal;
    }
    

}
