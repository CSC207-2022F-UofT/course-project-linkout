package screens.access_report_and_restrict_user;

import java.util.ArrayList;

public interface AccessReportViewModel {
    public void displayReport(String r1, String r2, String c, String t, ArrayList<String> se);
    public void failView();
}