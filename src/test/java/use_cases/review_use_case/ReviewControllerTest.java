package use_cases.review_use_case;

import entities.Review;
import org.junit.jupiter.api.Test;
import screens.review_screen.IReviewView;
import screens.review_screen.InMemoryReview;
import screens.review_screen.ReviewTestScreen;
import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.regular_user_register_use_case.UserRegisterDsRequestModel;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReviewControllerTest {

    @Test
    void addReview() throws IOException, InvalidAttributeValueException, IncompleteReviewException {
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
        ReviewController controller = new ReviewController(interactor);

        UserRegisterDsRequestModel userRegisterDsRequestModel = new UserRegisterDsRequestModel("Bob", "password", "password",
                null, null, null, null, null, null, null, null, null);
        userGateway.saveUser(userRegisterDsRequestModel);
        controller.addReview(1, "good guy", "Alice", "Bob");
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
        ReviewController controller = new ReviewController(interactor);

        Review review = new Review(5, "good guy", "Alice", "Bob", 1);
        reviewRepository.saveReview(review);

        controller.deleteReview(1);
    }
}