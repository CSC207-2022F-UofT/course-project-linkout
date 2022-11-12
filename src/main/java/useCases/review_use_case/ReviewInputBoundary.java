package useCases.review_use_case;

public interface ReviewInputBoundary {
    boolean checkReviewCompleteness(ReviewRequestModel requestModel);

    ReviewResponseModel addReview(ReviewRequestModel requestModel);

    ReviewResponseModel deleteReview(int id);

    ReviewResponseModel hideReview(int id);

    void updateRating(ReviewRequestModel requestModel);
}
