package use_cases.review_use_case;

import entities.Review;
import org.junit.jupiter.api.Test;
import screens.review_screen.IReviewView;
import screens.review_screen.ReviewTestScreen;
import use_cases.recommend_use_case.RecommendGateway;
import use_cases.regular_user_register_use_case.*;
import screens.review_screen.InMemoryReview;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReviewInteractorTest {

    public void initializeDataset() throws IOException, InterruptedException {
        RecommendGateway db = new RecommendGateway(System.getProperty("user.dir"));
        // Reinitiate dataset
        String[] command1 = {"rm", String.format("%s/src/main/data/likes.xls", db.getWorkingDir())};
        String[] command2 = {"cp", String.format("%s/src/main/data/data_storage/likes.xls", db.getWorkingDir()),
                String.format("%s/src/main/data", db.getWorkingDir())};
        String[] command3 = {"rm", String.format("%s/src/main/data/reviews.xls", db.getWorkingDir())};
        String[] command4 = {"cp", String.format("%s/src/main/data/data_storage/reviews.xls", db.getWorkingDir()),
                String.format("%s/src/main/data", db.getWorkingDir())};
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(command1);
        runtime.exec(command2);
        runtime.exec(command3);
        runtime.exec(command4).waitFor();

    }

    @Test
    void addReview() throws IOException, InvalidAttributeValueException, InterruptedException {
        ReviewGateway reviewRepository = new InMemoryReview();
        IReviewView iView =  new ReviewTestScreen();
        ReviewOutputBoundary presenter = new ReviewPresenter(iView) {
            @Override
            public ReviewResponseModel reportReview(ReviewResponseModel review) {
                assertEquals("added", review.getStatus());
                assertNotNull(review.getCreationTime());
                return null;
            }
        };

        UserGateway userGateway = new UserGateway(System.getProperty("user.dir"));
        ReviewInputBoundary interactor = new ReviewInteractor(
                presenter, reviewRepository, userGateway);

        UserRegisterDsRequestModel userRegisterDsRequestModel = new UserRegisterDsRequestModel("Bob", "password", "password",
                null, null, null, null, null, null, null, null, null);
        userGateway.saveUser(userRegisterDsRequestModel);
        ReviewRequestModel inputData = new ReviewRequestModel(
                1, "good guy", "Alice", "Bob");

        interactor.addReview(inputData);

        initializeDataset();

    }

    @Test
    void deleteReview() throws IOException, InvalidAttributeValueException {
        ReviewGateway reviewRepository = new InMemoryReview();
        IReviewView iView =  new ReviewTestScreen();
        ReviewOutputBoundary presenter = new ReviewPresenter(iView) {
            @Override
            public ReviewResponseModel reportReview(ReviewResponseModel review) {
                assertEquals("deleted", review.getStatus());
                assertNotNull(review.getCreationTime());
                return null;
            }
        };
        UserGateway userGateway = new UserGateway(System.getProperty("user.dir"));
        ReviewInputBoundary interactor = new ReviewInteractor(
                presenter, reviewRepository, userGateway);

        Review review = new Review(5, "good guy", "Alice", "Bob", 9999);
        reviewRepository.saveReview(review);

        interactor.deleteReview(9999);

    }

//    @Test
//    void hideReview() throws IOException, InvalidAttributeValueException {
//        ReviewGateway reviewRepository = new InMemoryReview();
//        IReviewView iView =  new ReviewTestScreen();
//        // This creates an anonymous implementing class for the Output Boundary.
//        ReviewOutputBoundary presenter = new ReviewPresenter(iView) {
//            @Override
//            public ReviewResponseModel reportReview(ReviewResponseModel review) {
//                // 4) Check that the Output Data and associated changes
//                // are correct
//                assertEquals("hided", review.getStatus());
//                assertNotNull(review.getCreationTime()); // any creation time is fine.
//                return null;
//            }
//        };
//        UserGateway userGateway = new UserGateway(System.getProperty("user.dir"));
//        ReviewInputBoundary interactor = new ReviewInteractor(
//                presenter, reviewRepository, userGateway);
//
//        // 2) Input data — we can make this up for the test. Normally it would
//        // be created by the Controller.
//        ReviewRequestModel inputData = new ReviewRequestModel(
//                1, "good guy", "Alice", "Bob");
//
//        // 3) Run the use case
//        interactor.addReview(inputData);
//        interactor.hideReview(1);
//
//    }
}
