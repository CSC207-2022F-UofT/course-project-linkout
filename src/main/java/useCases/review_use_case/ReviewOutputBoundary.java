package useCases.review_use_case;

public interface ReviewOutputBoundary {
    ReviewResponseModel reportReviewSuccess();
    ReviewResponseModel reportReviewFailure(String error);
}
