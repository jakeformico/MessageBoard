package group.project.messageboard.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import group.project.messageboard.models.Post;
import group.project.messageboard.repositories.PostRepository;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public Iterable<Post> getAllPersons() {
        return postRepository.findAll();
    }

    @PostMapping(value="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("/{id}")
    public Post updatePersonById(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> foundPost = postRepository.findById(id);
        return postRepository.updatePost(foundPost.get(), post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable Long id)
    {
        postRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable Long id)
    {
        return postRepository.findById(id).get();
    }

}
