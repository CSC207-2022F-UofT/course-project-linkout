package useCases.review_use_case;

import entities.DatabaseConnect;
import org.junit.jupiter.api.Test;
import presenter.ReviewPresenter;
import screen.InMemoryReview;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReviewInteractorTest {

    @Test
    void addReview() throws IOException, InvalidAttributeValueException {
        // To test the use case:
        // 1) Create a UserRegisterInteractor and prerequisite objects
        //    (arguments for the UserRegisterInteractor constructor parameters)
        // 2) create the Input Data
        // 3) Call the use case User Input Boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        // 1) UserRegisterInteractor and prerequisite objects
        // We're going to need a place to save and look up information. We could
        // use FileUser, but because unit tests are supposed to be independent
        // that would make us also reset the file when we are done.
        // Instead, we're going to "mock" that info using a short-lived solution
        // that just keeps the info in a dictionary, and it won't be persistent.
        // (Separately, elsewhere, we will need to test the FileUser works
        // properly.)
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
        ReviewInputBoundary interactor = new ReviewInteractor(
                presenter, reviewRepository, db);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        ReviewRequestModel inputData = new ReviewRequestModel(
                1, "good guy", "Alice", "Bob");

        // 3) Run the use case
        interactor.addReview(inputData);

    }

    @Test
    void deleteReview() throws IOException, InvalidAttributeValueException {
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
        ReviewInputBoundary interactor = new ReviewInteractor(
                presenter, reviewRepository, db);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        ReviewRequestModel inputData = new ReviewRequestModel(
                1, "good guy", "Alice", "Bob");

        // 3) Run the use case
        interactor.addReview(inputData);
        interactor.deleteReview(1, "Bob");

    }

    @Test
    void hideReview() throws IOException, InvalidAttributeValueException {
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
        ReviewInputBoundary interactor = new ReviewInteractor(
                presenter, reviewRepository, db);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        ReviewRequestModel inputData = new ReviewRequestModel(
                1, "good guy", "Alice", "Bob");

        // 3) Run the use case
        interactor.addReview(inputData);
        interactor.hideReview(1, "Bob");

    }
}