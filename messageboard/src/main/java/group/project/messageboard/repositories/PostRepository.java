package group.project.messageboard.repositories;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import group.project.messageboard.models.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

    public default Post updatePost(Post existingPost, Post newPost)
    {
        if(newPost.getTitle() != null)
            existingPost.setTitle(newPost.getTitle());
        if(newPost.getDateOfEvent() != null)
            existingPost.setDateOfEvent(newPost.getDateOfEvent());
        if(newPost.isApproved() != existingPost.isApproved())
            existingPost.setApproved(newPost.isApproved());
        if(newPost.getCreatedAt() != null)
            existingPost.setCreatedAt(newPost.getCreatedAt());
        if(newPost.getPerson() != null)
            existingPost.setPerson(newPost.getPerson());
        if(newPost.getDateOfExpiration() != null)
            existingPost.setDateOfExpiration(newPost.getDateOfExpiration());
        if(newPost.getDescription() != null)
            existingPost.setDescription(newPost.getDescription());
        if(newPost.getFile() != null)
            existingPost.setFile(newPost.getFile());
        return this.save(existingPost);
    }

    @Query("SELECT p.id, p.title, p.dateOfEvent FROM Post p WHERE p.isApproved = true AND p.dateOfExpiration > :today")
    Iterable<Post> getCalendarView(LocalDate today);

    @Query("SELECT p FROM Post p WHERE p.isApproved = true")
    Iterable<Post> getApprovedPosts();

    Iterable<Post> findByPersonId(Long personId);

    @Query("SELECT p FROM Post p JOIN p.person pe WHERE pe.id = :personId")
    Iterable<Post> getPostStatusByPersonId(Long personId);

    @Query("SELECT p.title, p.person, p.rejectionComments FROM Post p WHERE p.isApproved = false")
    Iterable<Post> getDenialReport();
}