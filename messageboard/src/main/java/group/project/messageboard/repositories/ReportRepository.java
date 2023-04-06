package group.project.messageboard.repositories;

import org.springframework.data.repository.CrudRepository;

import group.project.messageboard.models.Report;

public interface ReportRepository extends CrudRepository<Report, Long> {

    public default Report updatePost(Report exisiting, Report newFeed)
    {
        exisiting.setDate(newFeed.getDate());
        exisiting.setPostList(newFeed.getPostList());
        return this.save(exisiting);
    }
}