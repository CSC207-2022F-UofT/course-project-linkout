package record_review_use_case;
import java.util.ArrayList;

public class RecordReportInputData {
    private String reportingUserID;
    private String reportedUserID;
    private String category;
    private String reportText;
    private ArrayList<String> supportingEvidence;

    public RecordReportInputData(String r1, String r2, String c,
                                 String t, ArrayList<String> se) {
        reportingUserID = r1;
        reportedUserID = r2;
        category = c;
        reportText = t;
        supportingEvidence = new ArrayList<String>(se);
    }

    /*
    Getters.
     */
    public String getReportingUserID() { return reportingUserID; }
    public String getReportedUserID() { return reportedUserID; }
    public String getCategory() { return category; }
    public String getReportText() { return reportText; }
    public ArrayList<String> getSupportingEvidence() { return supportingEvidence; }
}
