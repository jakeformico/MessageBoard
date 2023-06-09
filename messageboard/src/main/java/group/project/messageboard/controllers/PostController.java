package group.project.messageboard.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import group.project.messageboard.models.Person;
import group.project.messageboard.models.Post;
import group.project.messageboard.services.PersonService;
import group.project.messageboard.services.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final PersonService personService;

    public PostController(PostService postService, PersonService personService) {
        this.postService = postService;
        this.personService = personService;
    }

    @GetMapping
    public Iterable<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping(value="/create/{personId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post, @PathVariable Long personId) throws Exception {
        Person foundPerson = personService.getById(personId);
        post.setPerson(foundPerson);
        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    public Post updatePostById(@PathVariable Long id, @RequestBody Post post) throws Exception {
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

    @GetMapping("/approvedPosts")
    public Iterable<Post> getApprovedPosts() {
        return postService.getApprovedPosts();
    }

    @GetMapping("/postStatusByPersonId/{personId}")
    public Iterable<Post> postStatusByPersonId(@PathVariable Long personId) {
        return postService.getPostStatusByPersonId(personId);
    }

    @GetMapping("/postStatusById/{postId}")
    public String postStatusByPostId(@PathVariable Long postId) {
        Post foundPost =  postService.getById(postId);
        return foundPost.isApproved() ? "Approved" : "Waiting for approval";
    }

    @GetMapping("/denialReport")
    public Iterable<Post> denialReport() {
        return postService.denialReport();
    }

    @GetMapping("/addPostToCalendar/{postId}")
    public String addPostToCalendar(@PathVariable Long postId) {
        postService.approvePostById(postId);
        return "Successfully added post with ID: " + postId + " to Calendar";
    }

    @GetMapping("/removePostFromCalendar/{postId}")
    public String removePostFromCalendar(@PathVariable Long postId) {
        postService.rejectPostById(postId);
        return "Successfully removed post with ID: " + postId + " from Calendar";
    }

}
