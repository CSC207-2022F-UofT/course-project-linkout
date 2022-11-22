package restrict_user_use_case;

import record_review_use_case.RecordReportInputData;

public interface RestrictUserInputBoundary {
    public RestrictUserOutputData restrictUser(RestrictUserInputData inputData);
    public RestrictUserOutputData accessReport(RestrictUserInputData restrictUserID);
}
