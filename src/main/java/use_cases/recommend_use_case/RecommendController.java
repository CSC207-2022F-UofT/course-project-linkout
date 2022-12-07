package use_cases.recommend_use_case;

import entities.User;
import use_cases.recommend_use_case.RecommendInputBoundary;
import use_cases.recommend_use_case.RecommendRequestModel;
import use_cases.recommend_use_case.RecommendResponseModel;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public class RecommendController {

    final RecommendInputBoundary recommendInput;

    public RecommendController(RecommendInputBoundary input) {
        this.recommendInput = input;
    }

    public RecommendResponseModel recommend(String username, String userSimilar) throws IOException, InvalidAttributeValueException {
        RecommendRequestModel requestModel = new RecommendRequestModel(username, userSimilar);

        return recommendInput.Recommend(requestModel);
    }

    public RecommendResponseModel recommend(String username) throws IOException, InvalidAttributeValueException {
        RecommendRequestModel requestModel = new RecommendRequestModel(username);

        return recommendInput.Recommend(requestModel);
    }

}
