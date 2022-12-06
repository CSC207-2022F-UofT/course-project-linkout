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

    JTextField location = new JTextField(15);

    JTextField gender = new JTextField(15);

    JTextField age = new JTextField(15);

    JTextField sexuality = new JTextField(15);

    JTextField hobbies = new JTextField(15);

    JTextField height = new JTextField(15);

    JTextField weight = new JTextField(15);

    JTextField contactInformation = new JTextField(15);

    JTextField selfDescription = new JTextField(15);
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
                    String.valueOf(age.getText()),
                    String.valueOf(sexuality.getText()),
                    String.valueOf(hobbies.getText()), String.valueOf(height.getText()),
                    String.valueOf(weight.getText()),
                    String.valueOf(contactInformation.getText()), String.valueOf(selfDescription.getText()));
            JOptionPane.showMessageDialog(this, username.getText() + " created.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}