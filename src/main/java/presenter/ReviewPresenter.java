package presenter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import useCases.review_use_case.ReviewOutputBoundary;
import useCases.review_use_case.ReviewResponseModel;

public class ReviewPresenter implements ReviewOutputBoundary{
    @Override
    public ReviewResponseModel reportReviewSuccess(ReviewResponseModel responseModel) {
        LocalDateTime responseTime = LocalDateTime.parse(responseModel.getCreationTime());
        responseModel.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        return responseModel;
    }

    @Override
    public ReviewResponseModel reportReviewFailure(String error) {
        throw new ReviewCreationFailed(error);
    }
}
