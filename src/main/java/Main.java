import controller.ReviewController;
import entities.UserFactory;
import presenter.ReviewPresenter;
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


    }
}
