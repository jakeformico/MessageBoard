package group.project.messageboard.repositories;

import org.springframework.data.repository.CrudRepository;

import group.project.messageboard.models.Feed;

public interface FeedRepository extends CrudRepository<Feed, Long> {

    public default Feed updateFeed(Feed exisiting, Feed newFeed)
    {
        exisiting.setRolling(newFeed.isRolling());
        exisiting.setDuration(newFeed.getDuration());
        exisiting.setShutoffDateTime(newFeed.getShutoffDateTime());
        exisiting.setCurrentDateTime(newFeed.getCurrentDateTime());
        exisiting.setContentList(newFeed.getContentList());
        return this.save(exisiting);
    }

    public default Feed setRolling(Feed existing)
    {
        existing.setRolling(true);
        return this.save(existing);
    }
}