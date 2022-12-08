package screens.record_report;

import screens.review_screen.LabelTextPanel;
import use_cases.record_report_use_case.*;
import use_cases.regular_user_register_use_case.UserGateway;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class ReportFrame extends JFrame implements ActionListener {
    RecordReportController controller;
    String userID;

    JTextField reportingUserID = new JTextField(15);
    JTextField reportText = new JTextField(15);
    JTextField additional = new JTextField(15);
    JComboBox<String> category;
    JButton ok, cancel;

    public static void main(String[] args) throws IOException {
        RecordReportOutputData recordReportOD = new RecordReportOutputData();
        RecordReportResultFrame viewReport = new RecordReportResultFrame();
        UserGateway recordReportGateway = new UserGateway(System.getProperty("user.dir"));
        RecordReportPresenter reportPresenter = new RecordReportPresenter(recordReportOD, viewReport);
        ReportDatabaseGateway recordReportDatabaseGateway = new ReportDatabase(System.getProperty("user.dir"));
        RecordReportInteractor reportInteractor = new RecordReportInteractor(reportPresenter, recordReportGateway,
                recordReportDatabaseGateway, "Admin");
        RecordReportController recordReportController = new RecordReportController(reportInteractor);
        ReportFrame frame = new ReportFrame(recordReportController, "User");
        frame.setVisible(true);
    }
    public ReportFrame(RecordReportController controller, String userID) {

        setBounds(150, 150, 780, 442);

        this.controller = controller;
        this.userID = userID;

        JLabel title = new JLabel("Report this user");
        title.setAlignmentX(0.5f);

        LabelTextPanel r1 = new LabelTextPanel(
                new JLabel("Your username:"), reportingUserID);
        LabelTextPanel re = new LabelTextPanel(
                new JLabel("Write your report here:"), reportText);
        LabelTextPanel se = new LabelTextPanel(
                new JLabel("Include any additional information here:"), additional);
        ok = new JButton("OK");
        cancel = new JButton("Cancel");

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
        cancel.addActionListener(e -> {
            this.dispose();
        });

        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
        );
        getContentPane().add(r1);
        getContentPane().add(re);
        getContentPane().add(se);
        this.add(category);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
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
        }
    }
}
