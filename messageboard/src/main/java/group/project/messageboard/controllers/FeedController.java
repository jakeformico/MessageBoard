package group.project.messageboard.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import group.project.messageboard.models.Feed;
import group.project.messageboard.repositories.FeedRepository;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

    private final FeedRepository feedRepository;

    public FeedController(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    @GetMapping
    public Iterable<Feed> getAll() {
        return feedRepository.findAll();
    }

    @PostMapping(value="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Feed create(@RequestBody Feed feed) {
        return feedRepository.save(feed);
    }

    @PutMapping("/{id}")
    public Feed updateById(@PathVariable Long id, @RequestBody Feed post) {
        Optional<Feed> foundPost = feedRepository.findById(id);
        return feedRepository.updatePost(foundPost.get(), post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id)
    {
        feedRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public Feed getById(@PathVariable Long id)
    {
        return feedRepository.findById(id).get();
    }

}
