package useCases.review_use_case;

public interface ReviewOutputBoundary {
    ReviewResponseModel reportReviewSuccess(ReviewResponseModel responseModel);
    ReviewResponseModel reportReviewFailure(String error);

}
