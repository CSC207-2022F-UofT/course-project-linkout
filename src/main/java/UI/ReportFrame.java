package UI;
import entities.Report;

import javax.swing.*;
import java.awt.event.*;

public class ReportFrame extends JFrame implements ActionListener {
    public ReportFrame() {
        JTextField reportingUserID = new JTextField("Your username");
        JTextField reportText = new JTextField("Write your report here");
        JTextField additional = new JTextField("Include any additional information here");
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");

        String[] categories = {
                "Bullying or harassment", "Hate speech or symbols",
                "Scam or fraud", "Violence", "Spam", "I just don't like it",
                "Other"};
        JComboBox<String> category = new JComboBox<String>(categories);
        category.setVisible(true);

        JPanel panel = new JPanel();
        //panel.add
        panel.add(category);
        panel.add(ok);
        panel.add(cancel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: This calls the RecordReportController

    }
}
