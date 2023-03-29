package group.project.messageboard.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import group.project.messageboard.models.Report;
import group.project.messageboard.repositories.ReportRepository;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final ReportRepository reportRepository;

    public ReportController(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @GetMapping
    public Iterable<Report> getAll() {
        return reportRepository.findAll();
    }

    @PostMapping(value="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Report create(@RequestBody Report report) {
        return reportRepository.save(report);
    }

    @PutMapping("/{id}")
    public Report updateById(@PathVariable Long id, @RequestBody Report post) {
        Optional<Report> foundPost = reportRepository.findById(id);
        return reportRepository.updatePost(foundPost.get(), post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id)
    {
        reportRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public Report getById(@PathVariable Long id)
    {
        return reportRepository.findById(id).get();
    }

}
