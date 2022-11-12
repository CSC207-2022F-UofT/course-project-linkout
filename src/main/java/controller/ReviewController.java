package controller;

import useCases.review_use_case.ReviewInputBoundary;
import useCases.review_use_case.ReviewRequestModel;
import useCases.review_use_case.ReviewResponseModel;

public class ReviewController {
    private final ReviewInputBoundary reviewInput;


    /**
     * Initialize a ReviewController
     *
     * @param reviewInput  a ReviewInputBoundary object
     */
    public ReviewController(ReviewInputBoundary reviewInput) {
        this.reviewInput = reviewInput;
    }

    public ReviewResponseModel addReview(float rating, String comment, String user) throws IncompleteReviewException {
        ReviewRequestModel requestModel = new ReviewRequestModel(rating, comment, user);
        if (reviewInput.checkReviewCompleteness(requestModel)) {
            return reviewInput.addReview(requestModel);
        }
        throw new IncompleteReviewException();
    }
}