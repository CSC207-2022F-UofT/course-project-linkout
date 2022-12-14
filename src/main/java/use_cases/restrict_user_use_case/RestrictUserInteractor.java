package use_cases.restrict_user_use_case;
import entities.*;
import use_cases.record_report_use_case.ReportDatabaseGateway;

public class RestrictUserInteractor implements RestrictUserInputBoundary{
    RestrictUserOutputBoundary restrictUserOB;
    RestrictUserGateway restrictUserGateway;
    ReportDatabaseGateway reportDatabaseGateway;
    boolean success;
    
    /**
     * Constructor for the restrict user and access report interactors. (They use
     * the same interactor since they are related use cases.)
     *
     * @param restrictUserOB        The instance of the output boundary to be used in the return.
     * @param reportDatabaseGateway The instance of the database gateway.
     * @param restrictUserGateway   The gateway instance.
     */
    public RestrictUserInteractor(RestrictUserOutputBoundary restrictUserOB, RestrictUserGateway restrictUserGateway,
                                  ReportDatabaseGateway reportDatabaseGateway) {
        this.restrictUserOB = restrictUserOB;
        this.restrictUserGateway = restrictUserGateway;
        this.reportDatabaseGateway = reportDatabaseGateway;
    }

    // The reported user will often be the user restricted, hence they use the same
    // controller, interactor, and presenter.

    /**
     * Accesses a report using the userID attribute from an instance of the RestrictUserInputData.
     * A user is obtained userID and their most recent report is obtained and passed to the presenter. If there is no
     * such report, an error message is passed to the presenter.
     *
     * @param inputData     The instance of the RestrictUserInputData which has several useful attributes
     * @return The displayReport or displayNoReportError of the restrictUserOutputBoundary instance.
     * This, in turn, returns an instance of the RestrictUserOutputData.
     */
    @Override
    public RestrictUserOutputData accessReport(RestrictUserInputData inputData) {
        try {
            Report r = reportDatabaseGateway.getReport(inputData.getReportID()); // Getting the newest report rather than the entire reports list is a design decision.
            RestrictUserOutputData outputData = new RestrictUserOutputData(
                    r.getReportingUserID(), r.getReportedUserID(), r.getCategory(),
                    r.getReportText(), r.getSupportingEvidence());
            return restrictUserOB.displayReport(outputData);
        } catch (Exception noReportError) {
            return restrictUserOB.displayNoReportError();
        }
    }

    /**
     * Restricts a user of the specified restriction duration (in seconds), which is passed in as part
     * of an instance of the RestrictUserInputData, when the duration is not zero. In any
     * case, the response view of the output boundary is called with the correct success state.
     *
     * @param restrictUserID The ID of the user to be restricted.
     * @return The responseView of the instance of the output boundary, which in turn
     * returns an instance of the RestrictUserOutputData.
     */
    @Override
    public RestrictUserOutputData restrictUser(RestrictUserInputData restrictUserID) {
        try {
            User user = restrictUserGateway.findUser(restrictUserID.getUserID());
            int r = restrictUserID.getRestrictionDuration();
            if (r != 0) {
                user.setRestrictionDuration(r);
                success = true;
            }
            return restrictUserOB.responseView(success);
        } catch (Exception e) {
            return restrictUserOB.responseView(success);
        }
    }
}
