package restrict_user_use_case;
import entities.*;
import gateway.DatabaseConnect;

public class RestrictUserInteractor implements RestrictUserInputBoundary{
    RestrictUserOutputBoundary restrictUserOB;
    boolean success;

    public RestrictUserInteractor(RestrictUserOutputBoundary restrictUserOB) {
        this.restrictUserOB = restrictUserOB;
    }

    @Override
    public RestrictUserOutputData accessReport(RestrictUserInputData inputData) {
        User user = DatabaseConnect.findUser(inputData.getUserID());
        Report r = user.getNewestReport(); // Getting the newest report rather than the entire reports list is a design decision.
        try {
            RestrictUserOutputData outputData = new RestrictUserOutputData(
                    r.getReportingUserID(), r.getReportedUserID(), r.getCategory(),
                    r.getReportText(), r.getSupportingEvidence());
            return restrictUserOB.displayReport(outputData);
        } catch (Exception noReportError) {
            return restrictUserOB.displayNoReportError();
        }
    }

    @Override
    public RestrictUserOutputData restrictUser(RestrictUserInputData restrictUserID) {
        User user = DatabaseConnect.findUser(inputData.getUserID());
        int r = restrictUserID.getRestrictionDuration();
        if (r == -1) {
            // Delete user
            success = true;
        } else if (r != 0) {
            user.setRestrictionDuration(r);
            success = true;
        }
        return restrictUserOB.responseView(success);
    }
}