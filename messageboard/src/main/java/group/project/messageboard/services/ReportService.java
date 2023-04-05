package group.project.messageboard.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import group.project.messageboard.models.Report;
import group.project.messageboard.repositories.ReportRepository;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Iterable<Report> getAll() {
        return reportRepository.findAll();
    }
    
    public Report create(Report report) {
        return reportRepository.save(report);
    }
    
    public Report updateById(Long id, Report post) {
        Optional<Report> foundPost = reportRepository.findById(id);
        return reportRepository.updatePost(foundPost.get(), post);
    }

    public void delete(Long id) {
        reportRepository.deleteById(id);
    }
    
    public Report getById(Long id) {
        return reportRepository.findById(id).get();
    }
}
