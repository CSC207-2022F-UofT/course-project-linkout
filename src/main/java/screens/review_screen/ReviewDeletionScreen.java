package screens.review_screen;

import controller.ReviewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class ReviewDeletionScreen extends JPanel implements ActionListener {
    JTextField receivername = new JTextField(15);
    JTextField id = new JTextField(15);

    /**
     * The controller
     */
    ReviewController reviewController;

    /**
     * A window with a title and a JButton.
     */
    public ReviewDeletionScreen(ReviewController controller) {

        this.reviewController = controller;

        JLabel title = new JLabel("Review Creation Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel receivernameInfo = new LabelTextPanel(
                new JLabel("Input receiver's name"), receivername);
        LabelTextPanel idInfo = new LabelTextPanel(
                new JLabel("Input id"), id);

        JButton delete = new JButton("Delete");

        JButton hide = new JButton("Hide");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(delete);
        buttons.add(hide);
        buttons.add(cancel);

        delete.addActionListener(this);
        hide.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(receivernameInfo);
        this.add(idInfo);
        this.add(buttons);

    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

        if (evt.getActionCommand().equals("Delete")){
        try {
            int idInt = parseInt(id.getText());
            reviewController.deleteReview(idInt);
            JOptionPane.showMessageDialog(this, receivername.getText() + "'s review deleted.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        if (evt.getActionCommand().equals("Hide")){
            try {
                int idInt = parseInt(id.getText());
                reviewController.hideReview(idInt);
                JOptionPane.showMessageDialog(this, receivername.getText() + "'s review hided.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }}

}
