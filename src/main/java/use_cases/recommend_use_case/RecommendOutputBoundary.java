package use_cases.recommend_use_case;

import entities.User;

import java.util.List;

public interface RecommendOutputBoundary {

    public List<User> PrepareRecommendView(RecommendResponseModel responseModel);


}
