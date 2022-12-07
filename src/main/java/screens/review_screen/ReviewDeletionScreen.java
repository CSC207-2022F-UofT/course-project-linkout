package screens.review_screen;

import use_cases.review_use_case.ReviewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class ReviewDeletionScreen extends JPanel implements ActionListener, IReviewView {
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

        JFrame application = new JFrame("Review Creation Screen");
        application.setLayout(new FlowLayout());
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        LabelTextPanel idInfo = new LabelTextPanel(
                new JLabel("Input id"), id);

        JButton delete = new JButton("Delete");

        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(delete);
        buttons.add(cancel);
        application.add(buttons);
        application.add(idInfo);
        application.setVisible(true);

        delete.addActionListener(this);
        cancel.addActionListener(this);


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
                JOptionPane.showMessageDialog(this,  "review deleted.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }}

//            if (evt.getActionCommand().equals("Hide")){
//                try {
//                    int idInt = parseInt(id.getText());
//                    reviewController.hideReview(idInt);
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(this, e.getMessage());
//                }
//            }
    }

    @Override
    public void updateMessage() {
        JOptionPane.showMessageDialog(this, "review deleted.");
    }
}

