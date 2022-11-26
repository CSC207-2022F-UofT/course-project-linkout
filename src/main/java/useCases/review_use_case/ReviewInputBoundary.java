package useCases.review_use_case;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface ReviewInputBoundary {
    ReviewResponseModel addReview(ReviewRequestModel requestModel) throws IOException, InvalidAttributeValueException;

    ReviewResponseModel deleteReview(int id, String receivername) throws IOException, InvalidAttributeValueException;

    ReviewResponseModel hideReview(int id, String receivername) throws IOException, InvalidAttributeValueException;
}
