package use_cases.recommend_use_case;

import entities.User;
import use_cases.recommend_use_case.RecommendOutputBoundary;
import use_cases.recommend_use_case.RecommendResponseModel;

import java.util.List;

public class RecommendPresenter implements RecommendOutputBoundary {


    @Override
    public List<User> PrepareRecommendView(RecommendResponseModel responseModel) {
        return responseModel.getAllUsers();
    }


}
