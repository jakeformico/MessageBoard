package group.project.messageboard.repositories;

import org.springframework.data.repository.CrudRepository;

import group.project.messageboard.models.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

}