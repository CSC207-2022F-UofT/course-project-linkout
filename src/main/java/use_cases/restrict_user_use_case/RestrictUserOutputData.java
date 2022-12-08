package use_cases.restrict_user_use_case;

public class RestrictUserOutputData {
    private String reportingUserID;
    private String reportedUserID;
    private String category;
    private String reportText;
    private String supportingEvidence;

    
    /**
     * The output data object constructor. Note this is related to the access report
     * use case only; the restrict user use case does not use the output data object.
     *
     * @param r1    The ID of the user who made the report.
     * @param r2    The ID of the user which the report concerns.
     * @param c     The category of the report.
     * @param t     The text of the report.
     * @param se    Any supporting evidence (e.g., links to images) for the report.
     */

    public RestrictUserOutputData(String r1, String r2, String c,
                                  String t, String se) {
        reportingUserID = r1;
        reportedUserID = r2;
        category = c;
        reportText = t;
        supportingEvidence = se;
    }

    /*
    Getters.
     */
    public String getReportingUserID() { return reportingUserID; }
    public String getReportedUserID() { return reportedUserID; }
    public String getCategory() { return category; }
    public String getReportText() { return reportText; }
    public String getSupportingEvidence() { return supportingEvidence; }

}

