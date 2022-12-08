package screens.access_report_and_restrict_user;

public class AccessRestrictTestScreen implements AccessReportResultViewModel{
    @Override
    public void displayReport(String r1, String r2, String c, String t, String se) { System.out.println("Test passed."); }
    @Override
    public void failView() { System.out.println("Test failed"); }
}
