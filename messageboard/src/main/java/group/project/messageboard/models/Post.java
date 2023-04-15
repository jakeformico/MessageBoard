package group.project.messageboard.models;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.Type;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt = new Date();

    @ManyToOne(cascade = CascadeType.MERGE)
    private Person person;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] file;

    private String description;
    private boolean isApproved;
    @Nonnull
    private String title;

    private LocalDate dateOfEvent;

    private LocalDate dateOfExpiration;

    private String rejectionComments;

    private String contentType;

    private Status status = Status.Pending;

    public enum Status{
        Pending, Approved, Denied
    }
}