package use_cases.restrict_user_use_case;

public class RestrictUserInputData {
    private String userID;
    private String reportID;
    private int restrictionDuration;

    /**
     * Constructor to be used for the restrictUser use case.
     * @param userID                The id of the user.
     * @param restrictionDuration   The time that the user is to be restricted for.
     */
    public RestrictUserInputData(String userID, int restrictionDuration) {
        this.userID = userID;
        this.restrictionDuration = restrictionDuration;
    }

    /**
     * Constructor to be used for the accessReport use case. The restriction duration
     * will not be used in this use case, so it is instantiated to 0.
     * @param reportID    The id of the report.
     */
    public RestrictUserInputData(String reportID) {
        this.reportID = reportID;
        this.restrictionDuration = 0;
    }

    /*
    Public getters.
     */
    public String getUserID() { return userID; }
    public String getReportID() { return reportID; }
    public int getRestrictionDuration() { return restrictionDuration; }
}
