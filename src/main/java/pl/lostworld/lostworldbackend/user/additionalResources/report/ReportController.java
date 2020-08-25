package pl.lostworld.lostworldbackend.user.additionalResources.report;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.lostworld.lostworldbackend.user.CurrentUser;
import pl.lostworld.lostworldbackend.utils.ResponseUtils;
import pl.lostworld.lostworldbackend.utils.ValidationUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @GetMapping("/checkAllForLoggedUser")
    public ResponseEntity<?> checkAllReportsForLoggedUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseUtils.designOkResponse(reportService.checkAllByUserId(currentUser.getId()));
    }

    @GetMapping("/add")
    public ResponseEntity<?> addReport() {
        return ResponseUtils.designOkResponse(new Report());
    }

    @PostMapping("/addForLoggedUser")
    public ResponseEntity<?> addReportForLoggedUser(@RequestBody Report report, @AuthenticationPrincipal CurrentUser currentUser) {
        Set<ConstraintViolation<Report>> violations = reportService.setUserAndValidate(report, currentUser.getActualUser());
        if (violations.isEmpty()) {
            return ResponseUtils.designCreatedResponse(reportService.setUserAndSave(report, currentUser.getActualUser()));
        } else {
            return ResponseUtils.designBadRequestResponse(ValidationUtils.mapViolationsForResponse(violations));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateReport (@Valid @RequestBody Report report) {
        return ResponseUtils.designOkResponse(reportService.save(report));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReport(@PathVariable Long id) {
        Optional<Report> report = reportService.deleteById(id);
        if (report.isPresent()) {
            return ResponseUtils.designOkResponse(report.get());
        } else {
            return ResponseUtils.designBadRequestSingletonResponse("No such element");
        }
    }
}
