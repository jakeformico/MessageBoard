package group.project.messageboard.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import group.project.messageboard.models.Feed;
import group.project.messageboard.repositories.FeedRepository;

@Service
public class FeedService {

    private final FeedRepository feedRepository;

    public FeedService(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
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
        Optional<Feed> foundPost = feedRepository.findById(id);
        return feedRepository.updatePost(foundPost.get(), post);
    }
    
    public void delete(Long id) {
        feedRepository.deleteById(id);
    }
    
    public Feed getById(Long id) {
        return feedRepository.findById(id).get();
    }
}
