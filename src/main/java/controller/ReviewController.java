package controller;

import useCases.review_use_case.ReviewInputBoundary;
import useCases.review_use_case.ReviewRequestModel;
import useCases.review_use_case.ReviewResponseModel;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

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


    /**
     * this method adds a review object to the receiver's review list by packing all inout info
     * into a ReviewRequestModel and calling the addReview method from reviewInput if the ReviewRequestModel
     * object is considered to be complete. Otherwise, it throws an IncompleteReviewException.
     *
     * @param rating rating of the review (1,2,3,4,5)
     * @param comment comment of the review
     * @param writer accountname of the writer
     * @param receiver accountname of the receiver
     * @return ReviewResponseModel
     * @throws IncompleteReviewException declaring the ReviewRequestModel is not complete
     */
    public ReviewResponseModel addReview(int rating, String comment, String writer, String receiver) throws IncompleteReviewException, IOException, InvalidAttributeValueException {
        ReviewRequestModel requestModel = new ReviewRequestModel(rating, comment, writer, receiver);
        if (requestModel.isComplete()) {
            return reviewInput.addReview(requestModel);
        }
        throw new IncompleteReviewException();
    }


    /**
     * this method deletes a review object from the receiver's review list if the receiver iputs thier accountname and
     * ID of the review to be deleted
     *
     * @param id ID of the review to be deleted
     * @param receivername accountname of the receiver
     * @return ReviewResponseModel
     */
    public ReviewResponseModel deleteReview(int id, String receivername) throws IOException, InvalidAttributeValueException {
        return reviewInput.deleteReview(id, receivername);
    }

    /**
     * this method hides a review object from the receiver's review list if the receiver iputs thier accountname and
     * ID of the review to be hided
     * @param id ID of the review to be hided
     * @param receivername accountname of the receiver
     * @return ReviewResponseModel
     */
    public ReviewResponseModel hideReview(int id, String receivername) throws IOException, InvalidAttributeValueException {
        return reviewInput.hideReview(id, receivername);
    }
}
