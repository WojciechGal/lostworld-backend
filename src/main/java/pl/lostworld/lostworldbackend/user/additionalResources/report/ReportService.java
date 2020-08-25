package pl.lostworld.lostworldbackend.user.additionalResources.report;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lostworld.lostworldbackend.user.User;
import pl.lostworld.lostworldbackend.utils.HibernateUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ReportService {

    private ReportRepository reportRepository;

    private Validator validator;

    public ReportService(ReportRepository reportRepository, Validator validator) {
        this.reportRepository = reportRepository;
        this.validator = validator;
    }

    public List<Report> checkAll() {
        return reportRepository.findAll();
    }

    public Optional<Report> checkById(Long id) {
        return reportRepository.findById(id);
    }

    public List<Report> checkAllByUserId(Long id) {
        return reportRepository.findAllByUserId(id);
    }

    public Report save(Report report) {
        return reportRepository.save(report);
    }

    public Report setUserAndSave(Report report, User user) {
        report.setUser(user);
        return reportRepository.save(report);
    }

    public Set<ConstraintViolation<Report>> setUserAndValidate(Report report, User user) {
        report.setUser(user);
        return validator.validate(report);
    }

    public Optional<Report> deleteById(Long id) {
        Optional<Report> report = reportRepository.findById(id);
        if (report.isPresent()) {
            //dociągnięcie danych jest wymagane przed usunięciem obiektu z DB
            HibernateUtils.initializeSequenceOfTerritoryEntities(report);
            reportRepository.deleteById(id);
            return report;
        } else {
            return report;
        }
    }

    public boolean checkIfExists(Long id) {
        return reportRepository.existsById(id);
    }
}
