package screens.review_screen;

import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.review_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class ReviewCreationScreen extends JFrame implements ActionListener, IReviewView {
    JTextField receiverName = new JTextField(15);
    JTextField writerName = new JTextField(15);
    JTextField comment = new JTextField(15);
    JTextField rating = new JTextField(15);

    public static void main(String[] args) throws IOException {
        IReviewViewImplementation viewReview = new IReviewViewImplementation();
        ReviewPresenter reviewPresenter = new ReviewPresenter(viewReview);
        ReviewGatewayImplementation reviewsGateway = new ReviewGatewayImplementation(System.getProperty("user.dir"));
        UserGateway userGateway = new UserGateway(System.getProperty("user.dir"));
        ReviewInputBoundary reviewInteractor = new ReviewInteractor(reviewPresenter, reviewsGateway, userGateway);
        ReviewController reviewController = new ReviewController(reviewInteractor);
        ReviewCreationScreen frame = new ReviewCreationScreen(reviewController);
        frame.setVisible(true);
    }

    /**
     * The controller
     */
    ReviewController reviewController;

    /**
     * A window with a title and a JButton.
     */
    public ReviewCreationScreen(ReviewController controller) {

        setBounds(150, 150, 680, 342);

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

        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
        );


        getContentPane().add(title);
        getContentPane().add(receivernameInfo);
        getContentPane().add(writernameInfo);
        getContentPane().add(commentInfo);
        getContentPane().add(ratingInfo);
        getContentPane().add(buttons);

    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

        try {
            int ratingInt = parseInt(rating.getText());
            JOptionPane.showMessageDialog(this, "Review is created sucessfully");
            reviewController.addReview(ratingInt, comment.getText(), writerName.getText(), receiverName.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @Override
    public void updateMessage() {
        JOptionPane.showMessageDialog(getContentPane(), receiverName.getText() + "'s review created.");
    }
}