package useCases.review_use_case;

public interface ReviewInputBoundary {
    boolean checkReviewCompleteness(ReviewRequestModel review);
    ReviewResponseModel createReview(ReviewRequestModel review);

}
