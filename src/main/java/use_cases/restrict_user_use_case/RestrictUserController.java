package use_cases.restrict_user_use_case;

public class RestrictUserController {
    RestrictUserInputBoundary restrictUserIB;

    public RestrictUserController (RestrictUserInputBoundary restrictUserIB) {
        this.restrictUserIB = restrictUserIB;
    }

    // The reported user will often be the user restricted, hence they use the same
    // controller, interactor, and presenter.

    public RestrictUserOutputData accessReport(String reportedUserID) {
        RestrictUserInputData inputData = new RestrictUserInputData(reportedUserID);
        return restrictUserIB.accessReport(inputData);
    }

    public RestrictUserOutputData restrictUser(String username, int restrictionDuration) {
        RestrictUserInputData restrictUserID = new RestrictUserInputData(username, restrictionDuration);
        return restrictUserIB.restrictUser(restrictUserID);
    }
}
