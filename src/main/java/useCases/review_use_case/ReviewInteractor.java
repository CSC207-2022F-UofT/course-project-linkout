package useCases.review_use_case;
import entities.*;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.time.LocalDateTime;

public class ReviewInteractor implements ReviewInputBoundary{
    private final ReviewOutputBoundary outputBoundary;
    private final ReviewGateway reviewGateway;
    private final DatabaseConnect db;


    /**
     * Initialize a ReviewInteractor
     * @param outputBoundary a ReviewOutputBoundary object
     * @param reviewGateway a ReviewGateway object
     * @param db a DatabaseConnect object
     */

    public ReviewInteractor(ReviewOutputBoundary outputBoundary, ReviewGateway reviewGateway, DatabaseConnect db) {
        this.outputBoundary = outputBoundary;
        this.reviewGateway = reviewGateway;
        this.db = db;
    }


    /**
     * this method adds the review object to the receiver's review list, save the review to the database and return a
     * ReviewResponseModel object which includes the content of the review and the status of the review.
     *
     * @param review a ReviewRequestModel object gathering the input info (rating, comment, writer's and receiver's accountname)
     * @return a ReviewResponseModel object which includes the content of the review and the status of the review (added, deleted, hided)
     */
    @Override
    public ReviewResponseModel addReview(ReviewRequestModel review) throws IOException, InvalidAttributeValueException {
        //TODO: find user by username, create the user instance
        User receiver = db.findUser(review.getReceiver());
        Review reviewObject = new Review(review.getRating(), review.getComment(), review.getWriter(), review.getReceiver());
        receiver.addReviews(reviewObject);
        reviewGateway.saveReview(reviewObject);
        //TODO: add this reviewObject to a User's review list
        //TODO: implement ReviewGateway to save this reviewObject
        String reviewString = "Review:\n" + "Comment: " + review.getComment() + "\n" +
                "Rating: " + review.getRating() + "\n" + "Writer: " + review.getWriter() + "\n" +
                "Receiver: " + review.getReceiver();
        LocalDateTime now = LocalDateTime.now();
        ReviewResponseModel response = new ReviewResponseModel(reviewString, "added", now.toString());
        return outputBoundary.reportReview(response);

    }

    /**
     * this method deletes the review object from the receiver's review list, move the review from the database and return a
     * ReviewResponseModel object which includes the content of the review and the status of the review (deleted).
     *
     * @param id ID of the review
     * @param receivername accountname of the receiver
     * @return a ReviewResponseModel object which includes the content of the review and the status of the review (added, deleted, hided)
     */
    @Override
    public ReviewResponseModel deleteReview(int id, String receivername) throws IOException, InvalidAttributeValueException {
        // TODO: implement the method
        User receiver = db.findUser(receivername);
        reviewGateway.moveReview(id, receiver);
        LocalDateTime now = LocalDateTime.now();
        ReviewResponseModel response = new ReviewResponseModel("deleted", now.toString());
        return outputBoundary.reportReview(response);
    }

    /**
     * this method hides the review object from the receiver's review list, do not delte the review from the database
     * and return a ReviewResponseModel object which includes the content of the review and the status of the
     * review (hided).
     *
     * @param id ID of the review
     * @param receivername accountname of the receiver
     * @return a ReviewResponseModel object which includes the content of the review and the status of the review (added, deleted, hided)
     */
    @Override
    public ReviewResponseModel hideReview(int id, String receivername) throws IOException, InvalidAttributeValueException {
        // TODO: implement the method
        User receiver = db.findUser(receivername);
        reviewGateway.softdeleteReview(id, receiver);
        LocalDateTime now = LocalDateTime.now();
        ReviewResponseModel response = new ReviewResponseModel("hided", now.toString());
        return outputBoundary.reportReview(response);
    }

}
