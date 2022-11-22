package entities;
import java.util.ArrayList;
import java.util.List;

public class Report {
    private String reportingUserID;
    private String reportedUserID;
    private static int count;
    private String reportID;
    private String category;
    private String reportText;
    private ArrayList<String> supportingEvidence;

    /**
     * Constructor for the report class. Note that the reportID is self-generated.
     *
     * @param r1    The ID of the user who made the report.
     * @param r2    The ID of the user which the report concerns.
     * @param c     The category of the report.
     * @param t     The text of the report.
     * @param se    Any supporting evidence (e.g., links to images) for the report.
     */
    public Report(String r1, String r2, String c, String t,
                  ArrayList<String> se) {
        reportingUserID = r1;
        reportedUserID = r2;
        reportID = Integer.toString(count++);
        category = c;
        reportText = t;
        supportingEvidence = new ArrayList<String>(se);
    }

    /*
    Getter methods. Remember to remove any unused getter methods (code smells).
     */
    public String getReportingUserID() { return reportingUserID; }
    public String getReportedUserID() { return reportedUserID; }
    public String getReportID() { return reportID; }
    public String getCategory() { return category; }
    public String getReportText() { return reportText; }
    public ArrayList<String> getSupportingEvidence() { return supportingEvidence; }

    /*
    Setter methods.
     */
    public void setCategory(String c) { category = c; }
}
