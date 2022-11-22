package record_review_use_case;
import entities.*;
import gateway.DatabaseConnect;

public class RecordReportInteractor implements RecordReportInputBoundary {
    RecordReportOutputBoundary recordReportOB;
    boolean success;

    public RecordReportInteractor(RecordReportOutputBoundary recordReportOB) {
        this.recordReportOB = recordReportOB;
    }
    @Override
    public RecordReportOutputData createReport(RecordReportInputData r) {
        Report report = new Report(r.getReportingUserID(), r.getReportedUserID(),
                r.getCategory(), r.getReportText(), r.getSupportingEvidence());

        User user = DatabaseConnect.findUser(r.getReportingUserID());
        Admin admin = DatabaseConnect.findRandomAdmin();
        if (user != null && admin != null) {
            user.addReport(report);
            String message = "A new report has been filed against " + report.getReportedUserID() + ". Check their reports attribute to find the report.";
            admin.addMessage(message);
            success = true;
        }

        return recordReportOB.responseView(success);
    }
}
