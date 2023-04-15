package group.project.messageboard.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import group.project.messageboard.models.Person;
import group.project.messageboard.models.Post;
import group.project.messageboard.models.Post.Status;
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
    
    public Post createPost(Post post) throws Exception {
    	// OCL 3
    	if (post.getDateOfExpiration().isBefore(LocalDate.now()))
    		throw new Exception("Expiration date cannot be before today.");
    	
        return postRepository.save(post);
    }
    
    public Post updatePostById(Long id, Post post) throws Exception {
    	// OCL 3
    	if (post.getDateOfExpiration().isBefore(LocalDate.now()))
    		throw new Exception("Expiration date cannot be before today.");
    	
        Optional<Post> foundPost = postRepository.findById(id);
        return postRepository.updatePost(foundPost.get(), post);
    }
    
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
    
    public Post getById(Long id) {
        Optional<Post> foundPost = postRepository.findById(id);
        return foundPost.get();
    }
    
    public Iterable<Post> getCalendarView() {
        LocalDate today = LocalDate.now();
        return postRepository.getCalendarView(today);
    }

    public Iterable<Post> getApprovedPosts() {
        return postRepository.getApprovedPosts();
    }

    
    public Post approvePostById(Long id) {
        Optional<Post> foundPost = postRepository.findById(id);
        foundPost.get().setStatus(Status.Approved);
        foundPost.get().setRejectionComments(null);
        return postRepository.save(foundPost.get());
    }
    
    public Post rejectPostById(Long id, String rejectionComments) {
        Optional<Post> foundPost = postRepository.findById(id);
        foundPost.get().setStatus(Status.Denied);        
        foundPost.get().setRejectionComments(rejectionComments);
        return postRepository.save(foundPost.get());
    }

    public Iterable<Post> getPostStatusByPersonId(Long id) {
        return postRepository.getPostStatusByPersonId(id);
    }

    public Iterable<Post> denialReport() {
        return postRepository.getDenialReport();
    }

}
