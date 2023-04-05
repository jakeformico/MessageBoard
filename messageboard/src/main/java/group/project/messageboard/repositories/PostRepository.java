package group.project.messageboard.repositories;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import group.project.messageboard.models.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

    public default Post updatePost(Post existingPost, Post newPost)
    {
        existingPost.setTitle(newPost.getTitle());
        existingPost.setDateOfEvent(newPost.getDateOfEvent());
        existingPost.setApproved(newPost.isApproved());
        existingPost.setCreatedAt(newPost.getCreatedAt());
        existingPost.setCreatorId(newPost.getCreatorId());
        existingPost.setDateOfExpiration(newPost.getDateOfExpiration());
        existingPost.setDescription(newPost.getDescription());
        existingPost.setUploadedFile(newPost.getUploadedFile());
        return this.save(existingPost);
    }

    @Query("SELECT p.id, p.title, p.dateOfEvent FROM Post p WHERE p.isApproved = true AND p.dateOfExpiration > :today")
    Iterable<Post> getCalendarView(LocalDate today);
}