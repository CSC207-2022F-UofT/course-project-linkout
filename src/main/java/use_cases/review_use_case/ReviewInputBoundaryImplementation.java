package use_cases.review_use_case;


import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public class ReviewInputBoundaryImplementation implements ReviewInputBoundary {

    public ReviewResponseModel addReview(ReviewRequestModel requestModel) throws
            IOException, InvalidAttributeValueException {
        String status = "added";
        String creationTime = ""; // Not sure how to get it yet
        ReviewResponseModel reviewResponseModel = new ReviewResponseModel(status, creationTime);
        return reviewResponseModel;
    }

    public ReviewResponseModel deleteReview(int id) throws IOException, InvalidAttributeValueException {
        String status = "deleted";
        String creationTime = ""; // Not sure how to get it yet
        ReviewResponseModel reviewResponseModel = new ReviewResponseModel(status, creationTime);
        return reviewResponseModel;
    }

    public ReviewResponseModel hideReview(int id) throws IOException, InvalidAttributeValueException {
        String status = "hided";
        String creationTime = ""; // Not sure how to get it yet
        ReviewResponseModel reviewResponseModel = new ReviewResponseModel(status, creationTime);
        return reviewResponseModel;
    }
}
