package use_cases.record_report_use_case;
import entities.*;

public class RecordReportInteractor implements RecordReportInputBoundary {
    RecordReportOutputBoundary recordReportOB;
    RecordReportGateway recordReportGateway;
    boolean success;
    String adminID;
    
    /**
     * The constructor of an interactor object.
     * @param recordReportOB        The instance of the output boundary.
     * @param recordReportGateway   The instance of the gateway.
     * @param adminID               The admin ID (to be determined outside this use case).
     */
    public RecordReportInteractor(RecordReportOutputBoundary recordReportOB, RecordReportGateway recordReportGateway, String adminID) {
        this.recordReportOB = recordReportOB;
        this.recordReportGateway = recordReportGateway;
        this.adminID = adminID;
    }
    
    /**
     * The primary function of the functionality of the record report use case.
     * A report object is created and stored in the user's reports attribute.
     * The admin is simultaneously notified. If this succeeds, the success view
     * is prepared. If this fails, the catch clause will be reached and the fail
     * view is prepared.
     *
     * @param r The instance of the input data.
     * @return  The responseView function.
     */
    @Override
    public RecordReportOutputData createReport(RecordReportInputData r) {
        Report report = new Report(r.getReportingUserID(), r.getReportedUserID(),
                r.getCategory(), r.getReportText(), r.getSupportingEvidence());

        try {
            User user = recordReportGateway.findUser(r.getReportingUserID());
            Admin admin = recordReportGateway.findAdmin(adminID);
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
