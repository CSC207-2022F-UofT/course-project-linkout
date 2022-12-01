package screens.record_report;
import javax.swing.*;
import java.awt.event.*;

public class UserPageFrame extends JFrame implements ActionListener {
    public UserPageFrame() {
        JButton reportUser = new JButton("Report this user");
        reportUser.addActionListener(this);

        JPanel buttons = new JPanel();
        buttons.add(reportUser);

        // Main layout.
        JPanel main = new JPanel();
        main.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == reportUser) {
//            //TODO: This calls the ReportFrame
//        }
    }
}
