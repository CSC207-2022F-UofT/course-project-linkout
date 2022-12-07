package screens.record_report;

import screens.review_screen.IReviewViewImplementation;
import screens.review_screen.LabelTextPanel;
import screens.review_screen.ReviewCreationScreen;
import use_cases.record_report_use_case.*;
import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.review_use_case.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ReportFrame extends JFrame implements ActionListener {
    RecordReportController controller;
    String userID;

    JTextField reportingUserID;
    JTextField reportText;
    JTextField additional;
    JComboBox<String> category;

    public static void main(String[] args) throws IOException {
        RecordReportOutputData recordReportOD = new RecordReportOutputData();
        RecordReportResultFrame viewReport = new RecordReportResultFrame();
        UserGateway recordReportGateway = new UserGateway(System.getProperty("user.dir"));
        RecordReportPresenter reportPresenter = new RecordReportPresenter(recordReportOD, viewReport);
        ReportDatabaseGateway recordReportDatabaseGateway = new ReportDatabase(System.getProperty("user.dir"));
        RecordReportInteractor reportInteractor = new RecordReportInteractor(reportPresenter, recordReportGateway,
                recordReportDatabaseGateway, "Admin");
        RecordReportController recordReportController = new RecordReportController(reportInteractor);
        ReportFrame frame = new ReportFrame(recordReportController, "Admin");
        frame.setVisible(true);
    }
    public ReportFrame(RecordReportController controller, String userID) {

        setBounds(150, 150, 780, 442);

        this.controller = controller;
        this.userID = userID;

        JLabel title = new JLabel("Report this user");
        title.setAlignmentX(0.5f);

        reportingUserID = new JTextField("Your username:");
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


        JPanel buttons = new JPanel();
        buttons.add(ok);
        buttons.add(cancel);

        ok.addActionListener(this);
        cancel.addActionListener(this);

        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
        );
        this.add(reportingUserID);
        this.add(reportText);
        this.add(additional);
        this.add(category);
        this.add(buttons);
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
                        additional.getText()
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
