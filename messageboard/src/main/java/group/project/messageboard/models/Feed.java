package group.project.messageboard.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private boolean isRolling;

    private long duration;

    @Nonnull
    private LocalDate shutoffDateTime;

    private LocalDate currentDateTime;

    @OneToMany(cascade = CascadeType.ALL)
    @Size(min = 0, max = 10)
    private List<Post> contentList = new ArrayList<>();

    public void addContent(Post content) {
        this.contentList.add(content);
    }
}