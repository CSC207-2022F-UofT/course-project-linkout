package screens.access_report_and_restrict_user;

import use_cases.record_report_use_case.ReportDatabase;
import use_cases.record_report_use_case.ReportDatabaseGateway;
import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.restrict_user_use_case.*;

import javax.swing.*;
import java.awt.event.*;

public class AdminStartScreen extends JFrame implements ActionListener {
    RestrictUserController controller;
    JButton restrict, access;

    public static void main(String[] args) {
//        RestrictUserGateway gateway = new UserGateway(System.getProperty("user.dir");
//        ReportDatabaseGateway database = new ReportDatabase(System.getProperty("user.dir");
//        RestrictUserOutputBoundary presenter = new RestrictUserPresenter();
//        RestrictUserInputBoundary interactor = new RestrictUserInteractor(presenter, gateway, database);
//        RestrictUserController controller = new RestrictUserController(interactor);
//        AdminStartScreen frame = new AdminStartScreen(controller);
//        frame.setVisible(true);
    }

    public AdminStartScreen(RestrictUserController controller) {
        setBounds(150, 150, 780, 442);
        this.controller = controller;

        restrict = new JButton("Restrict user");
        access = new JButton("Access report");
        JPanel buttons = new JPanel();
        buttons.add(restrict);
        buttons.add(access);
        restrict.addActionListener(this);
        access.addActionListener(this);

        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
        );
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == access) {
            AccessReportFrame frame = new AccessReportFrame(controller);
            frame.setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
        } else if (e.getSource() == restrict) {
            RestrictUserFrame frame = new RestrictUserFrame(controller);
            frame.setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
