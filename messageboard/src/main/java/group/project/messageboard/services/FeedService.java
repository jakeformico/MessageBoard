package group.project.messageboard.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import group.project.messageboard.models.*;
import group.project.messageboard.repositories.FeedRepository;
import group.project.messageboard.repositories.PostRepository;

@Service
public class FeedService {

    private final FeedRepository feedRepository;
    private final PostRepository postRepository;

    public FeedService(FeedRepository feedRepository, PostRepository postRepository) {
        this.feedRepository = feedRepository;
        this.postRepository = postRepository;
    }
    
    public Iterable<Feed> getAll() {
        return feedRepository.findAll();
    }
    
    public Feed create(Feed feed) throws Exception {
    	// OCL 2
    	if (feed.getContentList().size() > 10)
    		throw new Exception("Content list is limited to no more than 10.");
    	
        return feedRepository.save(feed);
    }
    
    public Feed updateById(Long id, Feed post) {
        Optional<Feed> foundFeed = feedRepository.findById(id);
        return feedRepository.updateFeed(foundFeed.get(), post);
    }
    
    public void delete(Long id) {
        feedRepository.deleteById(id);
    }
    
    public Feed getById(Long id) {
        return feedRepository.findById(id).get();
    }

    public Feed setRolling(Long feedId) {
        Optional<Feed> foundFeed = feedRepository.findById(feedId);
        return feedRepository.setRolling(foundFeed.get());
    }

    public Feed addPostToFeed(Long feedId, Long postId)
    {
        Post foundPost = postRepository.findById(postId).get();
        Feed foundFeed = feedRepository.findById(feedId).get();
        foundFeed.addContent(foundPost);
        return feedRepository.save(foundFeed);
    }
}
