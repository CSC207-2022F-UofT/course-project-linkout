package use_cases.access_report_and_restrict_user;

import entities.Report;
import entities.User;
import org.junit.jupiter.api.Test;
import screens.access_report_and_restrict_user.AccessReportResultViewModel;
import screens.access_report_and_restrict_user.AccessRestrictTestScreen;
import screens.record_report.TestingReportDatabase;
import use_cases.record_report_use_case.ReportDatabase;
import use_cases.record_report_use_case.ReportDatabaseGateway;
import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.restrict_user_use_case.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AccessReportAndRestrictUserTest {
    @Test
    public void accessReport() {
        ReportDatabaseGateway gateway = new TestingReportDatabase();
        try {
            gateway.saveReport(new Report("acc0", "acc1", "3", "Category", "Text", "SE"));
        } catch (Exception e) {
            System.out.println("Failed.");
        }
        RestrictUserGateway restrictUserGateway = new UserGateway(System.getProperty("user.dir"));
        AccessReportResultViewModel viewModel = new AccessRestrictTestScreen();
        RestrictUserOutputData outputData = new RestrictUserOutputData(
                "acc0", "acc1", "Category", "Text", "SE"
        );
        RestrictUserOutputBoundary presenter = new RestrictUserPresenter(outputData, viewModel) {
            @Override
            public RestrictUserOutputData displayReport(RestrictUserOutputData outputData) {
                assertEquals("Category", outputData.getCategory());
                return null;
            }
            @Override
            public RestrictUserOutputData displayNoReportError() {
                fail("Something wrong happened.");
                return null;
            }
        };

        RestrictUserInteractor interactor = new RestrictUserInteractor(presenter, restrictUserGateway, gateway);
        RestrictUserInputData inputData = new RestrictUserInputData("3");
        interactor.accessReport(inputData);
    }
}
