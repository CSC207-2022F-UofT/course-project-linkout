package use_cases.record_report_use_case;
import java.util.ArrayList;

public class RecordReportInputData {
    private String reportingUserID;
    private String reportedUserID;
    private String category;
    private String reportText;
    private ArrayList<String> supportingEvidence;

    /**
     * The constructor instantiating an input data object containing the
     * information relating to a report object.
     *
     * @param r1    The ID of the user who made the report.
     * @param r2    The ID of the user which the report concerns.
     * @param c     The category of the report.
     * @param t     The text of the report.
     * @param se    Any supporting evidence (e.g., links to images) for the report.
     */
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
