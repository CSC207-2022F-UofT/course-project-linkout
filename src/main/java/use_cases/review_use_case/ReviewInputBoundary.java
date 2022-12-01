package use_cases.review_use_case;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface ReviewInputBoundary {
    ReviewResponseModel addReview(ReviewRequestModel requestModel) throws IOException, InvalidAttributeValueException;

    ReviewResponseModel deleteReview(int id) throws IOException, InvalidAttributeValueException;

//    ReviewResponseModel hideReview(int id) throws IOException, InvalidAttributeValueException;
//    this method requires manipulating view which is implemented last, will do it if time permitted
}
