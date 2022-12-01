package screens.review_screen;

import use_cases.review_use_case.ReviewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class ReviewCreationScreen extends JPanel implements ActionListener, IReviewView {
    JTextField receiverName = new JTextField(15);
    JTextField writerName = new JTextField(15);
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

        LabelTextPanel receivernameInfo = new LabelTextPanel(
                new JLabel("Input receiver's name"), receiverName);
        LabelTextPanel writernameInfo = new LabelTextPanel(
                new JLabel("Input writer's name"), writerName);
        LabelTextPanel commentInfo = new LabelTextPanel(
                new JLabel("Input comment"), comment);
        LabelTextPanel ratingInfo = new LabelTextPanel(
                new JLabel("Input rating"), rating);


        JButton create = new JButton("Create");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(create);
        buttons.add(cancel);

        create.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(receivernameInfo);
        this.add(writernameInfo);
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
            int ratingInt = parseInt(rating.getText());
            reviewController.addReview(ratingInt, comment.getText(), writerName.getText(), receiverName.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @Override
    public void updateMessage() {
        JOptionPane.showMessageDialog(this, receiverName.getText() + "'s review created.");
    }
}
