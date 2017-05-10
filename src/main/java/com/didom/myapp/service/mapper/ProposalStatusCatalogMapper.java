package com.didom.myapp.service.mapper;

import com.didom.myapp.domain.*;
import com.didom.myapp.service.dto.ProposalStatusCatalogDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity ProposalStatusCatalog and its DTO ProposalStatusCatalogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProposalStatusCatalogMapper {

    ProposalStatusCatalogDTO proposalStatusCatalogToProposalStatusCatalogDTO(ProposalStatusCatalog proposalStatusCatalog);

    List<ProposalStatusCatalogDTO> proposalStatusCatalogsToProposalStatusCatalogDTOs(List<ProposalStatusCatalog> proposalStatusCatalogs);

    @Mapping(target = "proposals", ignore = true)
    @Mapping(target = "messages", ignore = true)
    ProposalStatusCatalog proposalStatusCatalogDTOToProposalStatusCatalog(ProposalStatusCatalogDTO proposalStatusCatalogDTO);

    List<ProposalStatusCatalog> proposalStatusCatalogDTOsToProposalStatusCatalogs(List<ProposalStatusCatalogDTO> proposalStatusCatalogDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default ProposalStatusCatalog proposalStatusCatalogFromId(Long id) {
        if (id == null) {
            return null;
        }
        ProposalStatusCatalog proposalStatusCatalog = new ProposalStatusCatalog();
        proposalStatusCatalog.setId(id);
        return proposalStatusCatalog;
    }
    

}
