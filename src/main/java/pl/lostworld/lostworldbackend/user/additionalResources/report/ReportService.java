package pl.lostworld.lostworldbackend.user.additionalResources.report;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReportService {

    private ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> checkAll() {
        return reportRepository.findAll();
    }

    public Report checkById(Long id) {
        return reportRepository.findById(id).orElse(null);
    }

    public Report add(Report report) {
        return reportRepository.save(report);
    }
}
