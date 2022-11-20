package useCases.review_use_case;
import entities.RegularUser;
import entities.Review;
import entities.User;
import entities.UserFactory;

import java.time.LocalDateTime;

public class ReviewInteractor implements ReviewInputBoundary{
    private final ReviewOutputBoundary outputBoundary;
    private final ReviewGateway reviewGateway;
    private final UserFactory userFactory;


    /**
     * Initialize a ReviewInteractor
     * @param outputBoundary a ReviewOutputBoundary object
     * @param reviewGateway a ReviewGateway object
     * @param userFactory a UserFactory object
     */

    public ReviewInteractor(ReviewOutputBoundary outputBoundary, ReviewGateway reviewGateway, UserFactory userFactory) {
        this.outputBoundary = outputBoundary;
        this.reviewGateway = reviewGateway;
        this.userFactory = userFactory;
    }

    @Override
    public boolean checkReviewCompleteness(ReviewRequestModel review) {
        return review.isComplete();
    }

    @Override
    public ReviewResponseModel addReview(ReviewRequestModel review){

        User user = new RegularUser(null, null, null);
        Review reviewObject = new Review(review.getRating(), review.getComment(), user);
        //TODO: add this reviewObject to a User's review list
        //TODO: implement ReviewGateway to save this reviewObject
        String reviewString = "Review:\n" + "Comment: " + review.getComment() + "\n" +
                "Rating: " + review.getRating() + "\n" + "User: " + review.getUser().toString();
        LocalDateTime now = LocalDateTime.now();
        return new ReviewResponseModel(reviewString, now.toString(), "added");

    }

    @Override
    public ReviewResponseModel deleteReview(int id){
        // TODO: implement the method
        return null;
    }

    @Override
    public ReviewResponseModel hideReview(int id){
        // TODO: implement the method
        return null;
    }

    @Override
    public void updateRating(ReviewRequestModel review){
        float rating = review.getRating();
        // TODO: implement the method
    }
}
