package group.project.messageboard.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import group.project.messageboard.models.Report;
import group.project.messageboard.services.ReportService;

@RestController
@RequestMapping("/api/report")
@CrossOrigin(origins = "http://localhost:3000")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public Iterable<Report> getAll() {
        return reportService.getAll();
    }

    @PostMapping(value="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Report create(@RequestBody Report report) {
        return reportService.create(report);
    }

    @PutMapping("/{id}")
    public Report updateById(@PathVariable Long id, @RequestBody Report post) {
        return reportService.updateById(id, post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        reportService.delete(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public Report getById(@PathVariable Long id) {
        return reportService.getById(id);
    }

}
