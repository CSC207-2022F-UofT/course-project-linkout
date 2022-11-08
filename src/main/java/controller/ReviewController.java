package controller;

import useCases.review_use_case.ReviewInputBoundary;
import useCases.review_use_case.ReviewRequestModel;
import useCases.review_use_case.ReviewResponseModel;

public class ReviewController {
    private final ReviewInputBoundary reviewInput;
    private final ReviewRequestModel requestModel;


    /**
     * Initialize a ReviewController
     *
     * @param reviewInput  a ReviewInputBoundary object
     * @param requestModel a ReviewRequestModel object
     */
    public ReviewController(ReviewInputBoundary reviewInput, ReviewRequestModel requestModel) {
        this.reviewInput = reviewInput;
        this.requestModel = requestModel;
    }

    public ReviewResponseModel createReview(ReviewRequestModel review) throws IncompleteReviewException {
        if (reviewInput.checkReviewCompleteness(review)) {
            return reviewInput.createReview(review);
        }
        throw new IncompleteReviewException();
    }
}