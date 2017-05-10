package com.didom.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Freelancer.
 */
@Entity
@Table(name = "freelancer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Freelancer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @ManyToOne
    private User user;

    @ManyToOne
    private Location location;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "freelancer_skill",
               joinColumns = @JoinColumn(name="freelancers_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="skills_id", referencedColumnName="id"))
    private Set<Skill> skills = new HashSet<>();

    @OneToMany(mappedBy = "freelancer")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Contract> contracts = new HashSet<>();

    @OneToMany(mappedBy = "freelancer")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Proposal> proposals = new HashSet<>();

    @OneToMany(mappedBy = "freelancer")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Message> messages = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public Freelancer image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Freelancer imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public User getUser() {
        return user;
    }

    public Freelancer user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public Freelancer location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public Freelancer skills(Set<Skill> skills) {
        this.skills = skills;
        return this;
    }

    public Freelancer addSkill(Skill skill) {
        this.skills.add(skill);
        skill.getFreelancers().add(this);
        return this;
    }

    public Freelancer removeSkill(Skill skill) {
        this.skills.remove(skill);
        skill.getFreelancers().remove(this);
        return this;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public Freelancer contracts(Set<Contract> contracts) {
        this.contracts = contracts;
        return this;
    }

    public Freelancer addContract(Contract contract) {
        this.contracts.add(contract);
        contract.setFreelancer(this);
        return this;
    }

    public Freelancer removeContract(Contract contract) {
        this.contracts.remove(contract);
        contract.setFreelancer(null);
        return this;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    public Set<Proposal> getProposals() {
        return proposals;
    }

    public Freelancer proposals(Set<Proposal> proposals) {
        this.proposals = proposals;
        return this;
    }

    public Freelancer addProposal(Proposal proposal) {
        this.proposals.add(proposal);
        proposal.setFreelancer(this);
        return this;
    }

    public Freelancer removeProposal(Proposal proposal) {
        this.proposals.remove(proposal);
        proposal.setFreelancer(null);
        return this;
    }

    public void setProposals(Set<Proposal> proposals) {
        this.proposals = proposals;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public Freelancer messages(Set<Message> messages) {
        this.messages = messages;
        return this;
    }

    public Freelancer addMessage(Message message) {
        this.messages.add(message);
        message.setFreelancer(this);
        return this;
    }

    public Freelancer removeMessage(Message message) {
        this.messages.remove(message);
        message.setFreelancer(null);
        return this;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Freelancer freelancer = (Freelancer) o;
        if (freelancer.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, freelancer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Freelancer{" +
            "id=" + id +
            ", image='" + image + "'" +
            ", imageContentType='" + imageContentType + "'" +
            '}';
    }
}
