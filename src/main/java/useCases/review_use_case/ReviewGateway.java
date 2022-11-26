package useCases.review_use_case;

import entities.Review;
import entities.User;

public interface ReviewGateway {
    void saveReview(Review review);
    void moveReview(int id, User user);
    void softdeleteReview(int id, User user);
//    List<Review> getReviews();
}
