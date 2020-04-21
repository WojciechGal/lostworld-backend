package pl.lostworld.lostworldbackend.user.additionalResources.report;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.lostworld.lostworldbackend.user.CurrentUser;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/checkAll")
    public List<Report> checkAllReports() {
        return reportService.checkAll();
    }

    @GetMapping("/add")
    public Report addReport(@AuthenticationPrincipal CurrentUser currentUser) {
        return reportService.createNewReportForUser(currentUser.getActualUser());
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Report addReport (@Valid @RequestBody Report report) {
        return reportService.add(report);
    }
}
