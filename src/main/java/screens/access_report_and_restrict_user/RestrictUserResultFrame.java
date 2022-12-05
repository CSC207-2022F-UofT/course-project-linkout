package screens.access_report_and_restrict_user;

import javax.swing.*;

public class RestrictUserResultFrame implements RestrictUserResultViewModel {
    @Override
    public void successView() {
        JOptionPane.showMessageDialog(null, "The user was successfully restricted!",
                "Success!", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void failView() {
        JOptionPane.showMessageDialog(null, "Either an incorrect restriction duration was given" +
                "(e.g., negative values other than -1) or the restriction duration was 0, in which case nothing" +
                "was to be done. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
