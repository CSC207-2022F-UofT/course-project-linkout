package screens.access_report_and_restrict_user;

import javax.swing.*;

public class AccessReportResultFrame implements AccessReportResultViewModel {
    @Override
    public void displayReport(String r1, String r2, String c, String t, String se) {
        String title = "The report that user " + r1 + " filed against user " + r2;
        String displayFormat = "Report category: " + c +
                "\nReport text: " + t + "\nAdditional information: " + se;
        JOptionPane.showMessageDialog(null, displayFormat, title, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void failView() {
        JOptionPane.showMessageDialog(null, "No report filed against the user with the given " +
                "username was found. Please try again.","Error", JOptionPane.ERROR_MESSAGE);
    }
}
