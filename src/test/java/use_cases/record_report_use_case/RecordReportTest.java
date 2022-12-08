package use_cases.record_report_use_case;

import entities.Report;
import org.junit.jupiter.api.Test;
import screens.record_report.RecordReportResultFrame;
import screens.record_report.RecordReportResultViewModel;
import screens.record_report.RecordReportTestScreen;
import screens.record_report.TestingReportDatabase;
import use_cases.regular_user_register_use_case.UserGateway;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RecordReportTest {
    @Test
    public void createReport() {
        Report r1 = new Report("Username 1", "Username 2",
                "Bullying or harassment", "I don't like username 2.",
                "Oh no.");
        assertEquals(r1.getReportText(), "I don't like username 2.");

        Report r2 = new Report("Username 1", "Username 2", "1", "Bullying or harassment", "what", "Oh no.");
        assertEquals(r2.getCategory(), "Bullying or harassment");
        assertEquals(r2.getReportID(), "1");
        assertEquals(r2.getReportedUserID(), "Username 2");
        assertEquals(r2.getReportingUserID(), "Username 1");
        assertEquals(r2.getReportText(), "what");
        assertEquals(r2.getSupportingEvidence(), "Oh no.");
    }
    @Test
    void recordReport() {
        ReportDatabaseGateway reportDatabaseGateway = new TestingReportDatabase();
        RecordReportGateway recordReportGateway = new UserGateway(System.getProperty("user.dir"));
        RecordReportOutputData outputData = new RecordReportOutputData();
        RecordReportResultViewModel viewModel = new RecordReportTestScreen();
        RecordReportOutputBoundary recordReportPresenter = new RecordReportPresenter(outputData, viewModel) {
            @Override
            public RecordReportOutputData responseView(boolean success) {
                assertTrue(success);
                return null;
            }
        };

        RecordReportInputBoundary interactor = new RecordReportInteractor(recordReportPresenter,
                recordReportGateway, reportDatabaseGateway,"Admin");
        RecordReportInputData inputData = new RecordReportInputData("acc0", "acc1", "1", "Text", "Additional info");
        interactor.createReport(inputData);
    }
}
