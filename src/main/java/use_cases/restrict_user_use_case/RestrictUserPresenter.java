package use_cases.restrict_user_use_case;

import screens.access_report_and_restrict_user.AccessReportResultViewModel;
import screens.access_report_and_restrict_user.RestrictUserResultViewModel;

public class RestrictUserPresenter implements RestrictUserOutputBoundary {
    RestrictUserOutputData restrictUserOutputData;
    AccessReportResultViewModel accessReportViewModel;
    RestrictUserResultViewModel restrictUserResultViewModel;
    
    /**
     * The constructor for the restrict user use case.
     * @param restrictUserOutputData        The instance of the output data.
     * @param restrictUserResultViewModel   The instance of the view model, in which
     *                                      the success or failure of a user restriction
     *                                      is presented.
     */
    public RestrictUserPresenter(RestrictUserOutputData restrictUserOutputData,
                                 RestrictUserResultViewModel restrictUserResultViewModel) {
        this.restrictUserOutputData = restrictUserOutputData;
        this.restrictUserResultViewModel = restrictUserResultViewModel;
    }
    
    /**
     * The constructor for the access report use case.
     * @param accessReportViewModel     The instance of the view model, in which
     *                                  the report will be presented.
     */
    public RestrictUserPresenter(AccessReportResultViewModel accessReportViewModel) {
        this.accessReportViewModel = accessReportViewModel;
    }
    
    /**
     * The function responsible for displaying the report.
     * @param outputData    The output data object.
     * @return              The output data object.
     */
    @Override
    public RestrictUserOutputData displayReport(RestrictUserOutputData outputData) {
        accessReportViewModel.displayReport(outputData.getReportingUserID(),
                outputData.getReportedUserID(), outputData.getCategory(),
                outputData.getReportText(), outputData.getSupportingEvidence());
        return outputData;
    }
    
    /**
     * To be used if a given report does not exist.
     * @return  The output data object.
     */
    @Override
    public RestrictUserOutputData displayNoReportError() {
        accessReportViewModel.failView();
        return restrictUserOutputData;
    }
    
    /**
     * Presents the result of restricting a user.
     * @param success   Whether it was success or failure.
     * @return          The output data object.
     */
    @Override
    public RestrictUserOutputData responseView(boolean success) {
        if (success) restrictUserResultViewModel.successView();
        else restrictUserResultViewModel.failView();
        return restrictUserOutputData;
    }
}
