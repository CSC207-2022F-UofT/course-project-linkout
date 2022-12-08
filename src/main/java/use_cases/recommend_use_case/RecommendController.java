package use_cases.recommend_use_case;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public class RecommendController {

    final RecommendInputBoundary recommendInput;

    /**
     * @param input the recommend interactor
     */
    public RecommendController(RecommendInputBoundary input) {
        this.recommendInput = input;
    }

    /**
     * @param username the user need to recommend
     * @param userSimilar the target user for similarity
     * @return a recommend response model with a list of users to recommend
     * @throws IOException Can't find database
     * @throws InvalidAttributeValueException Wrong type
     */
    public RecommendResponseModel recommend(String username, String userSimilar) throws IOException, InvalidAttributeValueException {
        RecommendRequestModel requestModel = new RecommendRequestModel(username, userSimilar);

        return recommendInput.Recommend(requestModel);
    }

    /**
     * @param username the user need to recommend
     * @return a recommend response model with a list of users to recommend
     * @throws IOException Can't find database
     * @throws InvalidAttributeValueException Wrong type
     */
    public RecommendResponseModel recommend(String username) throws IOException, InvalidAttributeValueException {
        RecommendRequestModel requestModel = new RecommendRequestModel(username);

        return recommendInput.Recommend(requestModel);
    }

}
