package screens.access_report_and_restrict_user;

import use_cases.restrict_user_use_case.RestrictUserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccessReportFrame extends JFrame implements ActionListener {
    RestrictUserController controller;
    JTextField reportID;

    public AccessReportFrame(RestrictUserController controller) {
        this.controller = controller;

        reportID = new JTextField("Enter the ID of the report to access:");
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");

        ok.addActionListener(this);
        cancel.addActionListener(this);

        JPanel buttons = new JPanel();
        buttons.add(ok);
        buttons.add(cancel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(reportID);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton) e.getSource()).getName().equals("ok")) {
            try {
                controller.accessReport(reportID.getText());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        } else if (((JButton) e.getSource()).getName().equals("cancel")) {
            //Do stuff
            System.out.println("Helo");
        }
    }
}
