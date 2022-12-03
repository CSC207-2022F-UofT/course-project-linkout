package use_cases.restrict_user_use_case;

public interface RestrictUserInputBoundary {
    public RestrictUserOutputData restrictUser(RestrictUserInputData inputData);
    public RestrictUserOutputData accessReport(RestrictUserInputData restrictUserID);
}
