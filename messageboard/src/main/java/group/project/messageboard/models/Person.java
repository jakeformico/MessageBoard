package group.project.messageboard.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nonnull
    private String firstName;

    @Nonnull
    private String lastName;

    @Nonnull
    private String email;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Post> postList = new ArrayList<Post>();

    public void addContent(Post post) {
        this.postList.add(post);
    }

    private Type type;

    public enum Type {
        OfficeManager, Student, Faculty, Admin
    }

}