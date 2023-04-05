package group.project.messageboard.models;

import java.time.LocalDate;
import java.util.Date;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private boolean isApproved;

    private String uploadedFile;

    private String description;

    @Nonnull
    private String title;

    private LocalDate dateOfEvent;

    private LocalDate dateOfExpiration;
}