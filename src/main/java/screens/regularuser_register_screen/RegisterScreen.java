package screens.regularuser_register_screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Frameworks/Drivers layer

public class RegisterScreen extends JPanel implements ActionListener {
    /**
     * The username chosen by the user
     */
    JTextField username = new JTextField(15);
    /**
     * The password
     */
    JPasswordField password = new JPasswordField(15);
    /**
     * The second password to make sure the user understands
     */
    JPasswordField repeatPassword = new JPasswordField(15);

    JPasswordField location = new JPasswordField(15);

    JPasswordField gender = new JPasswordField(15);

    JPasswordField age = new JPasswordField(15);

    JPasswordField sexuality = new JPasswordField(15);

    JPasswordField hobbies = new JPasswordField(15);

    JPasswordField height = new JPasswordField(15);

    JPasswordField weight = new JPasswordField(15);

    JPasswordField contactInformation = new JPasswordField(15);

    JPasswordField selfDescription = new JPasswordField(15);
    /**
     * The controller
     */
    UserRegisterController userRegisterController;

    /**
     * A window with a title and a JButton.
     */
    public RegisterScreen(UserRegisterController controller) {

        this.userRegisterController = controller;

        JLabel title = new JLabel("Register Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Choose username"), username);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Choose password"), password);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel("Enter password again"), repeatPassword);
        LabelTextPanel locationInfo = new LabelTextPanel(
                new JLabel("Enter location"), location);
        LabelTextPanel genderInfo = new LabelTextPanel(
                new JLabel("Enter gender"), gender);
        LabelTextPanel ageInfo = new LabelTextPanel(
                new JLabel("Enter age"), age);
        LabelTextPanel sexualityInfo = new LabelTextPanel(
                new JLabel("Enter sexuality"), sexuality);
        LabelTextPanel hobbiesInfo = new LabelTextPanel(
                new JLabel("Enter hobbies"), hobbies);
        LabelTextPanel heightInfo = new LabelTextPanel(
                new JLabel("Enter height"), height);
        LabelTextPanel weightInfo = new LabelTextPanel(
                new JLabel("Enter weight"), weight);
        LabelTextPanel contactInformationInfo = new LabelTextPanel(
                new JLabel("Enter contactInformation"), contactInformation);
        LabelTextPanel selfDescriptionInfo = new LabelTextPanel(
                new JLabel("Enter selfDescription"), selfDescription);

        JButton signUp = new JButton("Sign up");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(signUp);
        buttons.add(cancel);

        signUp.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(locationInfo);
        this.add(genderInfo);
        this.add(ageInfo);
        this.add(sexualityInfo);
        this.add(hobbiesInfo);
        this.add(heightInfo);
        this.add(weightInfo);
        this.add(contactInformationInfo);
        this.add(selfDescriptionInfo);
        this.add(buttons);

    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

        try {
            userRegisterController.create(username.getText(),
                    String.valueOf(password.getPassword()),
                    String.valueOf(repeatPassword.getPassword()),
                    String.valueOf(location.getText()), String.valueOf(gender.getText()),
                    String.valueOf(age.getPassword()),
                    String.valueOf(sexuality.getText()),
                    String.valueOf(hobbies.getText()), String.valueOf(height.getPassword()),
                    String.valueOf(weight.getPassword()),
                    String.valueOf(contactInformation.getPassword()), String.valueOf(selfDescription.getText()));
            JOptionPane.showMessageDialog(this, username.getText() + " created.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}