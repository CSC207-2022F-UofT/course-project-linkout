package controller;

import entities.User;
import use_cases.RecommendUseCase.RecommendInputBoundary;
import use_cases.RecommendUseCase.RecommendRequestModel;
import use_cases.RecommendUseCase.RecommendResponseModel;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public class RecommendController {

    final RecommendInputBoundary recommendInput;

    public RecommendController(RecommendInputBoundary input) {
        this.recommendInput = input;
    }

    RecommendResponseModel recommend(User user, String userSimilar) throws IOException, InvalidAttributeValueException {
        RecommendRequestModel requestModel = new RecommendRequestModel(user, userSimilar);

        return recommendInput.Recommend(requestModel);
    }

    RecommendResponseModel recommend(User user) throws IOException, InvalidAttributeValueException {
        RecommendRequestModel requestModel = new RecommendRequestModel(user);

        return recommendInput.Recommend(requestModel);
    }

}
