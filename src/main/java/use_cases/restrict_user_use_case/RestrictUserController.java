package use_cases.restrict_user_use_case;

public class RestrictUserController {
    RestrictUserInputBoundary restrictUserIB;
    
    /**
     * The constructor for the controller.
     * @param restrictUserIB    The instance of the input boundary.
     */
    public RestrictUserController (RestrictUserInputBoundary restrictUserIB) {
        this.restrictUserIB = restrictUserIB;
    }

    // The reported user will often be the user restricted, hence they use the same
    // controller, interactor, and presenter.
    
    /**
     * Initiates the access report function use case.
     *
     * @param reportedUserID    The ID of the reported user, under which the report is saved.
     * @return                  The accessReport method of the input boundary.
     */
    public RestrictUserOutputData accessReport(String reportedUserID) {
        RestrictUserInputData inputData = new RestrictUserInputData(reportedUserID);
        return restrictUserIB.accessReport(inputData);
    }
    
    /**
     * Initiates the restrict user use case.
     *
     * @param username              The ID of the user to be restricted (might be the user reported on in the
     *                              accessReport use case).
     * @param restrictionDuration   The time (in seconds) of the user's restriction.
     * @return                      The restrictUser function.
     */
    public RestrictUserOutputData restrictUser(String username, int restrictionDuration) {
        RestrictUserInputData restrictUserID = new RestrictUserInputData(username, restrictionDuration);
        return restrictUserIB.restrictUser(restrictUserID);
    }
}
