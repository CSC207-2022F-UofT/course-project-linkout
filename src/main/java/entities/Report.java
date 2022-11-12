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
    private List<String> supportingEvidence;

    /*
    Constructor for the report class. Note that the reportID is self-generated.
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
    public String getCategory() { return category; }

    /*
    Setter methods.
     */
    public void setCategory(String c) { category = c; }
}
