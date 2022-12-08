package screens.access_report_and_restrict_user;

import screens.review_screen.LabelTextPanel;
import use_cases.record_report_use_case.ReportDatabase;
import use_cases.record_report_use_case.ReportDatabaseGateway;
import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.restrict_user_use_case.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestrictUserFrame extends JFrame implements ActionListener {
    RestrictUserController controller;
    JTextField userID = new JTextField(15);
    JTextField time = new JTextField(15);
    JButton ok, cancel;

    public static void main(String[] args) {
        RestrictUserResultViewModel vm = new RestrictUserResultFrame();
        RestrictUserGateway gateway = new UserGateway(System.getProperty("user.dir"));
        ReportDatabaseGateway database = new ReportDatabase(System.getProperty("user.dir"));
        RestrictUserOutputBoundary presenter = new RestrictUserPresenter(vm);
        RestrictUserInputBoundary interactor = new RestrictUserInteractor(presenter, gateway, database);
        RestrictUserController controller = new RestrictUserController(interactor);
        AdminStartScreen frame = new AdminStartScreen(controller);
        frame.setVisible(true);
    }

    public RestrictUserFrame(RestrictUserController controller) {
        setBounds(150, 150, 780, 442);
        this.controller = controller;

        LabelTextPanel r = new LabelTextPanel(
                new JLabel("Enter the ID of the user to restrict:"), userID);
        LabelTextPanel t = new LabelTextPanel(
                new JLabel("Enter the time of restriction in seconds:"), time);
        ok = new JButton("OK");
        cancel = new JButton("Cancel");

        ok.addActionListener(this);
        cancel.addActionListener(e -> {
            this.dispose();
        });

        JPanel buttons = new JPanel();
        buttons.add(ok);
        buttons.add(cancel);

        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
        );
        getContentPane().add(r);
        getContentPane().add(t);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            try {
                controller.restrictUser(userID.getText(), Integer.parseInt(time.getText()));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        }
    }
}
