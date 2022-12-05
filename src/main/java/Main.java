
import Gateway.DatabaseConnect;
import use_cases.review_use_case.UserController;
import use_cases.review_use_case.UserPresenter;
import screens.review_screen.UserInfoScreen;
import use_cases.UserInputBoundary;
import use_cases.UserManagerInteractor;
import use_cases.UserOutputBoundary;
import entities.User;
import use_cases.recommend_use_case.RecommendController;
import use_cases.recommend_use_case.RecommendRequestModel;
import use_cases.recommend_use_case.RecommendResponseModel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {



        new UserInfoScreen();
//        JFrame application = new JFrame("Review Test");
//        CardLayout cardLayout = new CardLayout();
//        JPanel screens = new JPanel(cardLayout);
//        application.add(screens);
//
//        // Create the parts to plug into the Use Case+Entities engine
//        ReviewGateway review;
//        review = new FileReview("reviews.xls");
//        ReviewOutputBoundary presenter = new ReviewPresenter();
//        DatabaseConnect db = new DatabaseConnect(System.getProperty("user.dir"));
//        ReviewInputBoundary interactor = new ReviewInteractor(
//                presenter, review, db );
//        ReviewController reviewController = new ReviewController(interactor);
//
//        // Build the GUI, plugging in the parts
//        ReviewCreationScreen creationScreen = new ReviewCreationScreen(reviewController);
//        screens.add(creationScreen, "welcome");
//        cardLayout.show(screens, "create");
//        application.pack();
//        application.setVisible(true);
//
//
//        ReviewDeletionScreen deletionScreen = new ReviewDeletionScreen(reviewController);
//        screens.add(deletionScreen, "delete or hide");


    }
}
