package use_cases.record_report_use_case;

import entities.Report;

public interface ReportDatabaseGateway {
    void saveReport(Report report);
    Report getReport(String reportID);
}
