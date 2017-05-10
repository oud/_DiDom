package com.didom.myapp.service.mapper;

import com.didom.myapp.domain.*;
import com.didom.myapp.service.dto.MessageDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Message and its DTO MessageDTO.
 */
@Mapper(componentModel = "spring", uses = {FreelancerMapper.class, ClientMapper.class, ProposalMapper.class, ProposalStatusCatalogMapper.class, })
public interface MessageMapper {

    @Mapping(source = "freelancer.id", target = "freelancerId")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "proposal.id", target = "proposalId")
    @Mapping(source = "proposalStatusCatalog.id", target = "proposalStatusCatalogId")
    @Mapping(source = "proposalStatusCatalog.statusName", target = "proposalStatusCatalogStatusName")
    MessageDTO messageToMessageDTO(Message message);

    List<MessageDTO> messagesToMessageDTOs(List<Message> messages);

    @Mapping(source = "freelancerId", target = "freelancer")
    @Mapping(source = "clientId", target = "client")
    @Mapping(source = "proposalId", target = "proposal")
    @Mapping(source = "proposalStatusCatalogId", target = "proposalStatusCatalog")
    @Mapping(target = "attachments", ignore = true)
    Message messageDTOToMessage(MessageDTO messageDTO);

    List<Message> messageDTOsToMessages(List<MessageDTO> messageDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Message messageFromId(Long id) {
        if (id == null) {
            return null;
        }
        Message message = new Message();
        message.setId(id);
        return message;
    }
    

}
