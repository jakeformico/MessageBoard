package group.project.messageboard.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import group.project.messageboard.models.Post;
import group.project.messageboard.repositories.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    
    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }
    
    public Post createPost(Post post) {
        return postRepository.save(post);
    }
    
    public Post updatePostById(Long id, Post post) {
        Optional<Post> foundPost = postRepository.findById(id);
        return postRepository.updatePost(foundPost.get(), post);
    }
    
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
    
    public Post getById(Long id) {
        return postRepository.findById(id).get();
    }
    
    public Iterable<Post> getCalendarView() {
        LocalDate today = LocalDate.now();
        return postRepository.getCalendarView(today);
    }
    
    public Post approvePostById(Long id) {
        Optional<Post> foundPost = postRepository.findById(id);
        foundPost.get().setApproved(true);
        return postRepository.save(foundPost.get());
    }
    
    public Post rejectPostById(Long id) {
        Optional<Post> foundPost = postRepository.findById(id);
        foundPost.get().setApproved(false);
        return postRepository.save(foundPost.get());
    }

}
