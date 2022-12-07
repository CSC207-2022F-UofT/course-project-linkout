package use_cases.record_report_use_case;

import entities.Report;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface ReportDatabaseGateway {
    void saveReport(Report report) throws IOException, InvalidAttributeValueException;
    Report getReport(String reportID) throws IOException, InvalidAttributeValueException;
}
