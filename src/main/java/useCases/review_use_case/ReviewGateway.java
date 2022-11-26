package useCases.review_use_case;

import entities.Review;
import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

public interface ReviewGateway {

    Hashtable<Integer, List<Object>> getReviews(String usrname) throws IOException, InvalidAttributeValueException;

    void saveReview(Review review) throws IOException, InvalidAttributeValueException;

    void RemoveReview(int id, String username) throws IOException, InvalidAttributeValueException;

}
