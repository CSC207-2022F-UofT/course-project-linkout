package controller;

import entities.DatabaseConnect;
import org.junit.jupiter.api.Test;
import presenter.ReviewPresenter;
import screen.InMemoryReview;
import useCases.review_use_case.*;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReviewControllerTest {

    @Test
    void addReview() throws IncompleteReviewException, IOException, InvalidAttributeValueException {
        ReviewGateway reviewRepository = new InMemoryReview();

        // This creates an anonymous implementing class for the Output Boundary.
        ReviewOutputBoundary presenter = new ReviewPresenter() {
            @Override
            public ReviewResponseModel reportReview(ReviewResponseModel review) {
                // 4) Check that the Output Data and associated changes
                // are correct
                assertEquals("added", review.getStatus());
                assertNotNull(review.getCreationTime()); // any creation time is fine.
                return null;
            }
        };

        DatabaseConnect db = new DatabaseConnect(System.getProperty("user.dir"));
        ReviewInputBoundary interactor = new ReviewInteractor(presenter, reviewRepository, db);
        ReviewController controller = new ReviewController(interactor);

        // 3) Run the use case
        controller.addReview(1, "good guy", "Alice", "Bob");

    }



    @Test
    void deleteReview() throws IncompleteReviewException, IOException, InvalidAttributeValueException {
        ReviewGateway reviewRepository = new InMemoryReview();

        // This creates an anonymous implementing class for the Output Boundary.
        ReviewOutputBoundary presenter = new ReviewPresenter() {
            @Override
            public ReviewResponseModel reportReview(ReviewResponseModel review) {
                // 4) Check that the Output Data and associated changes
                // are correct
                assertEquals("deleted", review.getStatus());
                assertNotNull(review.getCreationTime()); // any creation time is fine.
                return null;
            }
        };

        DatabaseConnect db = new DatabaseConnect(System.getProperty("user.dir"));
        ReviewInputBoundary interactor = new ReviewInteractor(presenter, reviewRepository, db);
        ReviewController controller = new ReviewController(interactor);

        // 3) Run the use case
        controller.addReview(1, "good guy", "Alice", "Bob");
        controller.deleteReview(1, "Bob");


    }

    @Test
    void hideReview() throws IOException, InvalidAttributeValueException, IncompleteReviewException {
        ReviewGateway reviewRepository = new InMemoryReview();

        // This creates an anonymous implementing class for the Output Boundary.
        ReviewOutputBoundary presenter = new ReviewPresenter() {
            @Override
            public ReviewResponseModel reportReview(ReviewResponseModel review) {
                // 4) Check that the Output Data and associated changes
                // are correct
                assertEquals("hided", review.getStatus());
                assertNotNull(review.getCreationTime()); // any creation time is fine.
                return null;
            }
        };

        DatabaseConnect db = new DatabaseConnect(System.getProperty("user.dir"));
        ReviewInputBoundary interactor = new ReviewInteractor(presenter, reviewRepository, db);
        ReviewController controller = new ReviewController(interactor);

        // 3) Run the use case
        controller.addReview(1, "good guy", "Alice", "Bob");
        controller.hideReview(1, "Bob");
    }
}