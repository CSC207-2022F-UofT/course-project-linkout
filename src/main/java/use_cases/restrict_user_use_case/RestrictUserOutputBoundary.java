package use_cases.restrict_user_use_case;

public interface RestrictUserOutputBoundary {
    RestrictUserOutputData displayReport(RestrictUserOutputData outputData);
    RestrictUserOutputData displayNoReportError();
    RestrictUserOutputData responseView(boolean success);
}
