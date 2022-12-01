package screens.access_report_and_restrict_user;

import javax.swing.*;
import java.util.ArrayList;

public class AccessReportFrame implements AccessReportViewModel {
    @Override
    public void displayReport(String r1, String r2, String c, String t, ArrayList<String> se) {
        String title = "Report that user " + r1 + " filed against user " + r2;
        StringBuilder additional = new StringBuilder();
        for (String item: se) {
            if (additional.toString().equals("")) additional.append(item);
            else additional.append(", ").append(item);
        }

        String displayFormat = "Report category: " + c +
                "\nReport text: " + t + "\nAdditional information: " + additional.toString();
        JOptionPane.showMessageDialog(null, displayFormat, title, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void failView() {
        JOptionPane.showMessageDialog(null, "No report filed against the user with the given " +
                "username was found. Please try again.","Error", JOptionPane.ERROR_MESSAGE);
    }
}
