package screens.access_report_and_restrict_user;

import screens.review_screen.LabelTextPanel;
import use_cases.record_report_use_case.ReportDatabase;
import use_cases.record_report_use_case.ReportDatabaseGateway;
import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.restrict_user_use_case.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccessReportFrame extends JFrame implements ActionListener {
    RestrictUserController controller;
    JTextField reportID = new JTextField(15);
    JButton ok, cancel;

    public static void main(String[] args) {
        AccessReportResultViewModel vm = new AccessReportResultFrame();
        RestrictUserOutputData outputData = new RestrictUserOutputData("r1", "r2", "c", "t", "se");
        RestrictUserGateway gateway = new UserGateway(System.getProperty("user.dir"));
        ReportDatabaseGateway database = new ReportDatabase(System.getProperty("user.dir"));
        RestrictUserOutputBoundary presenter = new RestrictUserPresenter(outputData, vm);
        RestrictUserInputBoundary interactor = new RestrictUserInteractor(presenter, gateway, database);
        RestrictUserController controller = new RestrictUserController(interactor);
        AccessReportFrame frame = new AccessReportFrame(controller);
        frame.setVisible(true);
    }

    public AccessReportFrame(RestrictUserController controller) {
        setBounds(150, 150, 780, 442);
        this.controller = controller;

        LabelTextPanel r = new LabelTextPanel(
                new JLabel("Enter the ID of the report to access:"), reportID);
        ok = new JButton("OK");
        cancel = new JButton("Cancel");

        ok.addActionListener(this);
        cancel.addActionListener(e -> this.dispose());

        JPanel buttons = new JPanel();
        buttons.add(ok);
        buttons.add(cancel);

        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
        );
        getContentPane().add(r);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            try {
                controller.accessReport(reportID.getText());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        }
    }
}
