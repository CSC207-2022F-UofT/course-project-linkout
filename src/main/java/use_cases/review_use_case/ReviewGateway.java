package use_cases.review_use_case;

import entities.Review;
import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface ReviewGateway {

    void saveReview(Review review) throws IOException, InvalidAttributeValueException;

    void removeReview(int id, String username) throws IOException, InvalidAttributeValueException;

    Review findReview(int reviewId) throws IOException, InvalidAttributeValueException;

}
