package com.didom.myapp.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.didom.myapp.domain.enumeration.Duration;
import com.didom.myapp.domain.enumeration.Complexity;

/**
 * A DTO for the Job entity.
 */
public class JobDTO implements Serializable {

    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private BigDecimal paymentAmont;

    @NotNull
    private Duration expectedDuration;

    private Complexity complexity;

    private Long paymentTypeId;

    private Long clientId;

    private Long mainSkillId;

    private String mainSkillSkillName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getPaymentAmont() {
        return paymentAmont;
    }

    public void setPaymentAmont(BigDecimal paymentAmont) {
        this.paymentAmont = paymentAmont;
    }
    public Duration getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(Duration expectedDuration) {
        this.expectedDuration = expectedDuration;
    }
    public Complexity getComplexity() {
        return complexity;
    }

    public void setComplexity(Complexity complexity) {
        this.complexity = complexity;
    }

    public Long getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getMainSkillId() {
        return mainSkillId;
    }

    public void setMainSkillId(Long skillId) {
        this.mainSkillId = skillId;
    }

    public String getMainSkillSkillName() {
        return mainSkillSkillName;
    }

    public void setMainSkillSkillName(String skillSkillName) {
        this.mainSkillSkillName = skillSkillName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JobDTO jobDTO = (JobDTO) o;

        if ( ! Objects.equals(id, jobDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "JobDTO{" +
            "id=" + id +
            ", title='" + title + "'" +
            ", description='" + description + "'" +
            ", paymentAmont='" + paymentAmont + "'" +
            ", expectedDuration='" + expectedDuration + "'" +
            ", complexity='" + complexity + "'" +
            '}';
    }
}
