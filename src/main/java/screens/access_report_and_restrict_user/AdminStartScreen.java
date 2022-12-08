package screens.access_report_and_restrict_user;

import use_cases.record_report_use_case.ReportDatabase;
import use_cases.record_report_use_case.ReportDatabaseGateway;
import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.restrict_user_use_case.*;

import javax.swing.*;
import java.awt.event.*;

public class AdminStartScreen extends JFrame implements ActionListener {
    RestrictUserController controller, controller1;
    JButton restrict, access;

    public static void main(String[] args) {
        AccessReportResultViewModel vm = new AccessReportResultFrame();
        RestrictUserResultViewModel vm1 = new RestrictUserResultFrame();
        RestrictUserOutputData outputData = new RestrictUserOutputData("r1", "r2", "c", "t", "se");
        RestrictUserGateway gateway = new UserGateway(System.getProperty("user.dir"));
        ReportDatabaseGateway database = new ReportDatabase(System.getProperty("user.dir"));
        RestrictUserOutputBoundary presenter = new RestrictUserPresenter(outputData, vm);
        RestrictUserOutputBoundary presenter1 = new RestrictUserPresenter(vm1);
        RestrictUserInputBoundary interactor = new RestrictUserInteractor(presenter, gateway, database);
        RestrictUserInputBoundary interactor1 = new RestrictUserInteractor(presenter1, gateway, database);
        RestrictUserController controller = new RestrictUserController(interactor);
        RestrictUserController controller1 = new RestrictUserController(interactor1);
        AdminStartScreen frame = new AdminStartScreen(controller, controller1);
        frame.setVisible(true);
    }

    public AdminStartScreen(RestrictUserController controller, RestrictUserController controller1) {
        setBounds(150, 150, 780, 442);
        this.controller = controller;
        this.controller1 = controller1;

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
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else if (e.getSource() == restrict) {
            RestrictUserFrame frame = new RestrictUserFrame(controller1);
            frame.setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
