package com.didom.myapp.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Proposal entity.
 */
public class ProposalDTO implements Serializable {

    private Long id;

    @NotNull
    private ZonedDateTime proposalTime;

    @NotNull
    private BigDecimal paymentAmount;

    private Integer clientGrade;

    private String clientComment;

    private Integer freelancerGrade;

    private String freelancerComment;

    private Long jobId;

    private Long freelancerId;

    private Long paymentTypeId;

    private Long currentProposalStatusId;

    private String currentProposalStatusStatusName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public ZonedDateTime getProposalTime() {
        return proposalTime;
    }

    public void setProposalTime(ZonedDateTime proposalTime) {
        this.proposalTime = proposalTime;
    }
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    public Integer getClientGrade() {
        return clientGrade;
    }

    public void setClientGrade(Integer clientGrade) {
        this.clientGrade = clientGrade;
    }
    public String getClientComment() {
        return clientComment;
    }

    public void setClientComment(String clientComment) {
        this.clientComment = clientComment;
    }
    public Integer getFreelancerGrade() {
        return freelancerGrade;
    }

    public void setFreelancerGrade(Integer freelancerGrade) {
        this.freelancerGrade = freelancerGrade;
    }
    public String getFreelancerComment() {
        return freelancerComment;
    }

    public void setFreelancerComment(String freelancerComment) {
        this.freelancerComment = freelancerComment;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(Long freelancerId) {
        this.freelancerId = freelancerId;
    }

    public Long getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Long getCurrentProposalStatusId() {
        return currentProposalStatusId;
    }

    public void setCurrentProposalStatusId(Long proposalStatusCatalogId) {
        this.currentProposalStatusId = proposalStatusCatalogId;
    }

    public String getCurrentProposalStatusStatusName() {
        return currentProposalStatusStatusName;
    }

    public void setCurrentProposalStatusStatusName(String proposalStatusCatalogStatusName) {
        this.currentProposalStatusStatusName = proposalStatusCatalogStatusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProposalDTO proposalDTO = (ProposalDTO) o;

        if ( ! Objects.equals(id, proposalDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProposalDTO{" +
            "id=" + id +
            ", proposalTime='" + proposalTime + "'" +
            ", paymentAmount='" + paymentAmount + "'" +
            ", clientGrade='" + clientGrade + "'" +
            ", clientComment='" + clientComment + "'" +
            ", freelancerGrade='" + freelancerGrade + "'" +
            ", freelancerComment='" + freelancerComment + "'" +
            '}';
    }
}
