package record_review_use_case;
import entities.Report;

public class RecordReportInteractor implements RecordReportInputBoundary {
    RecordReportOutputBoundary recordReportOB;

    public RecordReportInteractor(RecordReportOutputBoundary recordReportOB) {
        this.recordReportOB = recordReportOB;
    }

    @Override
    public RecordReportOutputData createReport(RecordReportInputData r) {
        Report report = new Report(r.getReportingUserID(), r.getReportedUserID(),
                r.getCategory(), r.getReportText(), r.getSupportingEvidence());

        boolean success = false;
        // Call UserManager or something like that to add the report to a user's
        // reports attribute. May need a user list to search for the user.
        //if (reportedUser in userList) userList.get(reportedUser.accountName).addReport(report);
        // success = true;
        return recordReportOB.showResult(success);
    }
}
