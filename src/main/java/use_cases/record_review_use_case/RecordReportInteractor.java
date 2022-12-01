package use_cases.record_review_use_case;
import entities.*;
import Gateway.UserGateway;

public class RecordReportInteractor implements RecordReportInputBoundary {
    RecordReportOutputBoundary recordReportOB;
    RecordReportGateway recordReportGateway;
    boolean success;
    String adminID;

    public RecordReportInteractor(RecordReportOutputBoundary recordReportOB, RecordReportGateway recordReportGateway, String adminID) {
        this.recordReportOB = recordReportOB;
        this.recordReportGateway = recordReportGateway;
        this.adminID = adminID;
    }

    @Override
    public RecordReportOutputData createReport(RecordReportInputData r) {
        Report report = new Report(r.getReportingUserID(), r.getReportedUserID(),
                r.getCategory(), r.getReportText(), r.getSupportingEvidence());

        try {
            User user = recordReportGateway.findUser(r.getReportingUserID());
            Admin admin = (Admin) recordReportGateway.findUser(adminID);
            if (user != null && admin != null) {
                user.addReport(report);
                String message = "A new report has been filed against " + report.getReportedUserID() + ". Check their reports attribute to find the report.";
                admin.addMessage(message);
                success = true;
            }

            return recordReportOB.responseView(success);
        } catch (Exception e) {
            return recordReportOB.responseView(success);
        }
    }
}
