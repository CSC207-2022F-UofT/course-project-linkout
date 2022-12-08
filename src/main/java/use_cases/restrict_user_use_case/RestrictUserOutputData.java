package use_cases.restrict_user_use_case;

import java.util.ArrayList;

public class RestrictUserOutputData {
    private String reportingUserID;
    private String reportedUserID;
    private String category;
    private String reportText;
    private String supportingEvidence;

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