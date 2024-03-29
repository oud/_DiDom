package com.didom.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Skill.
 */
@Entity
@Table(name = "skill")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Skill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "skill_name", nullable = false)
    private String skillName;

    @ManyToMany(mappedBy = "skills")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Freelancer> freelancers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public Skill skillName(String skillName) {
        this.skillName = skillName;
        return this;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Set<Freelancer> getFreelancers() {
        return freelancers;
    }

    public Skill freelancers(Set<Freelancer> freelancers) {
        this.freelancers = freelancers;
        return this;
    }

    public Skill addFreelancer(Freelancer freelancer) {
        this.freelancers.add(freelancer);
        freelancer.getSkills().add(this);
        return this;
    }

    public Skill removeFreelancer(Freelancer freelancer) {
        this.freelancers.remove(freelancer);
        freelancer.getSkills().remove(this);
        return this;
    }

    public void setFreelancers(Set<Freelancer> freelancers) {
        this.freelancers = freelancers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Skill skill = (Skill) o;
        if (skill.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, skill.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Skill{" +
            "id=" + id +
            ", skillName='" + skillName + "'" +
            '}';
    }
}
