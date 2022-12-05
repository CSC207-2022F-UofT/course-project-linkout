package use_cases.review_use_case;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import screens.review_screen.IReviewView;

public class ReviewPresenter implements ReviewOutputBoundary{

    private IReviewView iView;

    public ReviewPresenter(IReviewView iView) {
        this.iView = iView;
    }

    /**
     * this method reports the status pf a review after an action (add,delete,hide) is performed on this
     * review，along with the time the action is performed.
     *
     * @param responseModel a ReviewResponseModel containing the content, status and action time of a review
     *                      when the review is added. When a review is deleted or hided, the response model will
     *                      only have the status and action time of the review.
     * @return ReviewResponseModel
     */
    @Override
    public ReviewResponseModel reportReview(ReviewResponseModel responseModel) {
        LocalDateTime responseTime = LocalDateTime.parse(responseModel.getCreationTime());
        responseModel.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        iView.updateMessage();
        return responseModel;
    }

}
