package group.project.messageboard.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import group.project.messageboard.models.Feed;
import group.project.messageboard.services.FeedService;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

    private final FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @GetMapping
    public Iterable<Feed> getAll() {
        return feedService.getAll();
    }

    @PostMapping(value="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Feed create(@RequestBody Feed feed) {
        return feedService.create(feed);
    }

    @PutMapping("/{id}")
    public Feed updateById(@PathVariable Long id, @RequestBody Feed post) {
        return feedService.updateById(id, post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        feedService.delete(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public Feed getById(@PathVariable Long id) {
        return feedService.getById(id);
    }

}
