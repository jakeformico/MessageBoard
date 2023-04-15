package group.project.messageboard.models;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PostWrapper {
    private MultipartFile file;

    private String description;

    private String title;

    private LocalDate dateOfEvent;

    private LocalDate dateOfExpiration;
}
