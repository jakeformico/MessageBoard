package group.project.messageboard.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import group.project.messageboard.models.Post;
import group.project.messageboard.services.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Iterable<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping(value="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    public Post updatePostById(@PathVariable Long id, @RequestBody Post post) {
        return postService.updatePostById(id, post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable Long id) {
        return postService.getById(id);
    }

    @GetMapping("/calendarView")
    public Iterable<Post> getCalendarView() {
        return postService.getCalendarView();
    }

    @PutMapping("/approve/{id}")
    public Post approvePostById(@PathVariable Long id) {
        return postService.approvePostById(id);
    }

    @PutMapping("/reject/{id}")
    public Post rejectPostById(@PathVariable Long id) {
        return postService.rejectPostById(id);
    }

}
