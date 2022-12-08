package screens.record_report;

import javax.swing.*;

public class RecordReportResultFrame implements RecordReportResultViewModel {
    @Override
    public void successView() {
        JOptionPane.showMessageDialog(null,
                "Your review has been submitted.",
                "Success!", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void failView() {
        JOptionPane.showMessageDialog(null,
                "Something went wrong. Please try again.",
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }
}
