package screen;
import controller.ReviewController;
import useCases.review_use_case.ReviewRequestModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Float.parseFloat;
import static javax.swing.JOptionPane.showMessageDialog;

public class ReviewCreationScreen extends JPanel implements ActionListener{
    JTextField username = new JTextField(15);
    JTextField comment = new JTextField(15);
    JTextField rating = new JTextField(15);

    /**
     * The controller
     */
    ReviewController reviewController;

    /**
     * A window with a title and a JButton.
     */
    public ReviewCreationScreen(ReviewController controller) {

        this.reviewController = controller;

        JLabel title = new JLabel("Review Creation Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Choose username"), username);
        LabelTextPanel commentInfo = new LabelTextPanel(
                new JLabel("Add comment"), comment);
        LabelTextPanel ratingInfo = new LabelTextPanel(
                new JLabel("Add rating"), rating);

        JButton create = new JButton("Create");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(create);
        buttons.add(cancel);

        create.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(commentInfo);
        this.add(ratingInfo);
        this.add(buttons);

    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

        try {
            float ratingFloat = parseFloat(rating.getText());
            reviewController.addReview(ratingFloat, comment.getText(), username.getText());
            JOptionPane.showMessageDialog(this, username.getText() + "created.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

}
