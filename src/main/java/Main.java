import controller.ReviewController;
import entities.UserFactory;
import presenter.ReviewPresenter;
import screen.FileReview;
import screen.ReviewCreationScreen;
import useCases.review_use_case.ReviewGateway;
import useCases.review_use_case.ReviewInputBoundary;
import useCases.review_use_case.ReviewInteractor;
import useCases.review_use_case.ReviewOutputBoundary;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame application = new JFrame("Review Test");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        // Create the parts to plug into the Use Case+Entities engine
        ReviewGateway review;
        review = new FileReview("reviews.csv");
        ReviewOutputBoundary presenter = new ReviewPresenter();
        UserFactory userFactory = new UserFactory();
        ReviewInputBoundary interactor = new ReviewInteractor(
                presenter, review, userFactory );
        ReviewController reviewController = new ReviewController(interactor);

        // Build the GUI, plugging in the parts
        ReviewCreationScreen creationScreen = new ReviewCreationScreen(reviewController);
        screens.add(creationScreen, "welcome");
        cardLayout.show(screens, "create");
        application.pack();
        application.setVisible(true);

        // Unused screens; we'll uncomment this later
//        WelcomeScreen welcomeScreen = new WelcomeScreen();
//        LoginScreen loginScreen = new LoginScreen();
//        LoggedInScreen loggedInScreen = new LoggedInScreen();
//        screens.add(welcomeScreen, "register");
//        screens.add(loginScreen, "login");
//        screens.add(loggedInScreen, "loggedIn");

    }
}
