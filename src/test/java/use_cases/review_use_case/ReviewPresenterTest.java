package use_cases.review_use_case;

import org.junit.jupiter.api.Test;
import screens.review_screen.IReviewView;
import screens.review_screen.ReviewTestScreen;

import static org.junit.jupiter.api.Assertions.*;

class ReviewPresenterTest {

    @Test
    void reportReview() {
        ReviewResponseModel input = new ReviewResponseModel("good guy", "added", "2022-12-02T12:43:38.328949");
        IReviewView screen = new ReviewTestScreen(){
            public void updateMessage(){
                assertNotNull(input.getCreationTime());
            }
        };
        ReviewOutputBoundary presenter = new ReviewPresenter(screen);
        presenter.reportReview(input);

    }
}