package screens.record_report;

import use_cases.record_review_use_case.RecordReportController;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class ReportFrame extends JFrame implements ActionListener {
    RecordReportController controller;
    String userID;

    JTextField reportingUserID;
    JTextField reportText;
    JTextField additional;
    JComboBox<String> category;
    public ReportFrame(RecordReportController controller, String userID) {
        this.controller = controller;
        this.userID = userID;

        JLabel title = new JLabel("Report this user");
        title.setAlignmentX(0.5f);

        reportingUserID = new JTextField("Your username");
        reportText = new JTextField("Write your report here");
        additional = new JTextField("Include any additional information here");
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");

        //Dropdown option menu
        String[] categories = {
                "Bullying or harassment", "Hate speech or symbols",
                "Scam or fraud", "Violence", "Spam", "I just don't like it",
                "Other"};
        category = new JComboBox<String>(categories);

        ok.addActionListener(this);
        cancel.addActionListener(this);

        JPanel buttons = new JPanel();
        buttons.add(ok);
        buttons.add(cancel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(reportingUserID);
        this.add(reportText);
        this.add(additional);
        this.add(category);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton) e.getSource()).getName().equals("ok")) {
            try {
                controller.createReport(
                        reportingUserID.getText(),
                        userID,
                        Integer.toString(category.getSelectedIndex()),
                        reportText.getText(),
                        new ArrayList<String>(Collections.singletonList(additional.getText()))
                );
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        } else if (((JButton) e.getSource()).getName().equals("cancel")) {
            //Do stuff
            System.out.println("Helo");
        }
    }
}
