package use_cases.restrict_user_use_case;

import screens.access_report_and_restrict_user.AccessReportViewModel;
import screens.access_report_and_restrict_user.RestrictUserResultViewModel;

public class RestrictUserPresenter implements RestrictUserOutputBoundary {
    RestrictUserOutputData restrictUserOutputData;
    AccessReportViewModel accessReportViewModel;
    RestrictUserResultViewModel restrictUserResultViewModel;

    public RestrictUserPresenter(RestrictUserOutputData restrictUserOutputData,
                                 RestrictUserResultViewModel restrictUserResultViewModel) {
        this.restrictUserOutputData = restrictUserOutputData;
        this.restrictUserResultViewModel = restrictUserResultViewModel;
    }

    public RestrictUserPresenter(AccessReportViewModel accessReportViewModel) {
        this.accessReportViewModel = accessReportViewModel;
    }

    @Override
    public RestrictUserOutputData displayReport(RestrictUserOutputData outputData) {
        accessReportViewModel.displayReport(outputData.getReportingUserID(),
                outputData.getReportedUserID(), outputData.getCategory(),
                outputData.getReportText(), outputData.getSupportingEvidence());
        return outputData;
    }

    @Override
    public RestrictUserOutputData displayNoReportError() {
        accessReportViewModel.failView();
        return restrictUserOutputData;
    }

    @Override
    public RestrictUserOutputData responseView(boolean success) {
        if (success) restrictUserResultViewModel.successView();
        else restrictUserResultViewModel.failView();
        return restrictUserOutputData;
    }
}
