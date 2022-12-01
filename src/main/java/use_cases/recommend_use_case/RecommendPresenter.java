package use_cases.recommend_use_case;

import entities.User;

import java.util.List;

public class RecommendPresenter implements RecommendOutputBoundary {


    @Override
    public List<User> PrepareRecommendView(RecommendResponseModel responseModel) {
        return responseModel.getAllUsers();
    }


}
