package presenter;

import useCases.review_use_case.ReviewOutputBoundary;
import useCases.review_use_case.ReviewResponseModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReviewPresenter implements ReviewOutputBoundary {
    private ReviewResponseModel responseModel;

    public ReviewPresenter(ReviewResponseModel responseModel) {
        this.responseModel = responseModel;
    }

    @Override
    public ReviewResponseModel reportReviewSuccess() {
        LocalDateTime responseTime = LocalDateTime.parse(responseModel.getCreationTime());
        responseModel.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        return responseModel;
    }

    @Override
    public ReviewResponseModel reportReviewFailure(String error) {
        throw new ReviewCreationFailed(error);
    }

    public String presentReview(){
        return responseModel.toString();
    }

}
