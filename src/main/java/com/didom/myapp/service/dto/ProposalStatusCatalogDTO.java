package com.didom.myapp.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the ProposalStatusCatalog entity.
 */
public class ProposalStatusCatalogDTO implements Serializable {

    private Long id;

    private String statusName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProposalStatusCatalogDTO proposalStatusCatalogDTO = (ProposalStatusCatalogDTO) o;

        if ( ! Objects.equals(id, proposalStatusCatalogDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProposalStatusCatalogDTO{" +
            "id=" + id +
            ", statusName='" + statusName + "'" +
            '}';
    }
}
