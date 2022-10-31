package entities;
import java.util.ArrayList;

public class Report {
    String reportingUserID;
    String reportedUserID;
    String reportID;
    String category;
    String reportText;
    ArrayList<Report> supportingEvidence = new ArrayList<Report>();
}
