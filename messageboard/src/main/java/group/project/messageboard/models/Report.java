package group.project.messageboard.models;

import jakarta.persistence.Entity;
import java.time.LocalDate;
import java.util.List;


import java.util.ArrayList;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import lombok.Data;

@Data
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    public void addContent(Post post) {
        this.postList.add(post);
    }
}