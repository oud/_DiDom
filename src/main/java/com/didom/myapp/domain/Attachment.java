package com.didom.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Attachment.
 */
@Entity
@Table(name = "attachment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Attachment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attachment_link")
    private String attachmentLink;

    @ManyToOne
    private Message message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttachmentLink() {
        return attachmentLink;
    }

    public Attachment attachmentLink(String attachmentLink) {
        this.attachmentLink = attachmentLink;
        return this;
    }

    public void setAttachmentLink(String attachmentLink) {
        this.attachmentLink = attachmentLink;
    }

    public Message getMessage() {
        return message;
    }

    public Attachment message(Message message) {
        this.message = message;
        return this;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attachment attachment = (Attachment) o;
        if (attachment.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, attachment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Attachment{" +
            "id=" + id +
            ", attachmentLink='" + attachmentLink + "'" +
            '}';
    }
}
