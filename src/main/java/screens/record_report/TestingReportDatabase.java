package screens.record_report;

import entities.Report;
import use_cases.record_report_use_case.ReportDatabaseGateway;

import java.util.HashMap;
import java.util.Map;

public class TestingReportDatabase implements ReportDatabaseGateway {
    final private Map<String, Report> reports = new HashMap<>();
    @Override
    public void saveReport(Report report) {
        reports.put(report.getReportID(), report);
    }

    @Override
    public Report getReport(String id) {
        return reports.get(id);
    }
}
