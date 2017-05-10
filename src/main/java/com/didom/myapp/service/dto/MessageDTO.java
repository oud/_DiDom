package com.didom.myapp.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Message entity.
 */
public class MessageDTO implements Serializable {

    private Long id;

    private ZonedDateTime messageTime;

    private String messageText;

    private Long freelancerId;

    private Long clientId;

    private Long proposalId;

    private Long proposalStatusCatalogId;

    private String proposalStatusCatalogStatusName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public ZonedDateTime getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(ZonedDateTime messageTime) {
        this.messageTime = messageTime;
    }
    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Long getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(Long freelancerId) {
        this.freelancerId = freelancerId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getProposalId() {
        return proposalId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

    public Long getProposalStatusCatalogId() {
        return proposalStatusCatalogId;
    }

    public void setProposalStatusCatalogId(Long proposalStatusCatalogId) {
        this.proposalStatusCatalogId = proposalStatusCatalogId;
    }

    public String getProposalStatusCatalogStatusName() {
        return proposalStatusCatalogStatusName;
    }

    public void setProposalStatusCatalogStatusName(String proposalStatusCatalogStatusName) {
        this.proposalStatusCatalogStatusName = proposalStatusCatalogStatusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MessageDTO messageDTO = (MessageDTO) o;

        if ( ! Objects.equals(id, messageDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
            "id=" + id +
            ", messageTime='" + messageTime + "'" +
            ", messageText='" + messageText + "'" +
            '}';
    }
}
