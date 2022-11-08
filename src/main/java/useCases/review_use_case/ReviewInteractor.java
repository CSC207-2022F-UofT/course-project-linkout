package useCases.review_use_case;

import entities.Review;
import entities.User;

import java.time.LocalDateTime;

public class ReviewInteractor implements ReviewInputBoundary{

    private final ReviewOutputBoundary outputBoundary;
    private final ReviewGateway reviewGateway;
    private final ReviewRequestModel requestModel;
    private final User user;


    /**
     * Initialize a ReviewInteractor
     * @param outputBoundary a ReviewOutputBoundary object
     * @param reviewGateway a ReviewGateway object
     * @param requestModel a ReviewRequestModel object
     * @param user a User object
     */

    public ReviewInteractor(ReviewOutputBoundary outputBoundary, ReviewGateway reviewGateway, ReviewRequestModel requestModel, User user) {
        this.outputBoundary = outputBoundary;
        this.reviewGateway = reviewGateway;
        this.requestModel = requestModel;
        this.user = user;
    }

    @Override
    public boolean checkReviewCompleteness(ReviewRequestModel review) {
        return this.requestModel.isComplete();
    }
    @Override
    public ReviewResponseModel createReview(ReviewRequestModel review) {
        String reviewString = "Review:\n" + "Comment: " + review.getComment() + "\n" +
                "Rating: " + review.getRating() + "\n" + "User: " + review.getUser().toString();
        LocalDateTime now = LocalDateTime.now();
        return new ReviewResponseModel(reviewString, now.toString(), null);
    }

    public ReviewResponseModel addReview(ReviewRequestModel review){
        Review reviewObject = new Review(review.getRating(), review.getComment(), review.getUser());
        //TODO: add this reviewObject to a User's review list
        //TODO: implement ReviewGateway to save this reviewObject
        return null;

    }

    public ReviewResponseModel deleteReview(int id){
        // TODO: implement the method
        return null;
    }

    public ReviewResponseModel hideReview(int id){
        // TODO: implement the method
        return null;
    }

    public void updateRating(ReviewRequestModel review){
        int rating = review.getRating();
        // TODO: implement the method
    }
}
