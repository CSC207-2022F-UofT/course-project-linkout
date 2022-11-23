package useCases.review_use_case;

import entities.Review;
import entities.User;

import java.util.List;

public interface ReviewGateway {
    void saveReview(List<Review> reviews);
    void moveReview(int id, User user);
    void softdeleteReview(int id, User user);
    List<Review> getReviews();
}
