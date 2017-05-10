package com.didom.myapp.service.mapper;

import com.didom.myapp.domain.*;
import com.didom.myapp.service.dto.AttachmentDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Attachment and its DTO AttachmentDTO.
 */
@Mapper(componentModel = "spring", uses = {MessageMapper.class, })
public interface AttachmentMapper {

    @Mapping(source = "message.id", target = "messageId")
    AttachmentDTO attachmentToAttachmentDTO(Attachment attachment);

    List<AttachmentDTO> attachmentsToAttachmentDTOs(List<Attachment> attachments);

    @Mapping(source = "messageId", target = "message")
    Attachment attachmentDTOToAttachment(AttachmentDTO attachmentDTO);

    List<Attachment> attachmentDTOsToAttachments(List<AttachmentDTO> attachmentDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Attachment attachmentFromId(Long id) {
        if (id == null) {
            return null;
        }
        Attachment attachment = new Attachment();
        attachment.setId(id);
        return attachment;
    }
    

}
