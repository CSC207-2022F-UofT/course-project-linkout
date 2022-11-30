package use_cases.RecommendUseCase;

import entities.User;
import use_cases.RecommendUseCase.RecommendOutputBoundary;
import use_cases.RecommendUseCase.RecommendResponseModel;

import java.util.List;

public class RecommendPresenter implements RecommendOutputBoundary {


    @Override
    public List<User> PrepareRecommendView(RecommendResponseModel responseModel) {
        return responseModel.getAllUsers();
    }


}
